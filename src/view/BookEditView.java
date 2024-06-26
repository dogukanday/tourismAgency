package view;

import business.*;
import core.ComboItem;
import core.Helper;
import entities.*;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.text.MaskFormatter;
import java.text.ParseException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

public class BookEditView extends Layout {
    private JPanel container;
    private JLabel lbl_name;
    private JTextField fld_name;
    private JTextField fld_mpno;
    private JFormattedTextField fld_checkin;
    private JFormattedTextField fld_checkout;
    private JTextField fld_adult;
    private JTextField fld_child;
    private JTextField fld_idno;
    private JComboBox cmb_rooms;
    private JTextField fld_price;
    private JButton btn_save;
    private JLabel lbl_mpno;
    private JLabel lbl_checkin;
    private JLabel lbl_checkout;
    private JLabel lbl_adult;
    private JLabel lbl_child;
    private JLabel lbl_idno;
    private JLabel lbl_room;
    private JLabel lbl_price;
    private BookManager bookManager;
    private Book book;
    private Rooms rooms;
    private RoomsManager roomsManager;
    private HotelManager hotelManager;
    private SeasonManager seasonManager;
    private Season season;
    private Hotel hotel;
    private Pension pension;
    private PensionManager pensionManager;

    public BookEditView(Book book) {
        this.add(container);
        this.guiInit(400, 700, "Rezervasyon Ekle / Düzenle");
        this.book = book;
        this.bookManager = new BookManager();
        this.rooms = new Rooms();
        this.roomsManager = new RoomsManager();
        this.hotel = new Hotel();
        this.hotelManager = new HotelManager();
        this.pension = new Pension();
        this.pensionManager = new PensionManager();
        this.seasonManager = new SeasonManager();
        this.season = new Season();

        fld_price.setEditable(false);

        cmbBoxItems();

        if (book.getId() != 0) {
            fld_name.setText(book.getName());
            fld_mpno.setText(book.getMpno());
            fld_checkin.setText(String.valueOf(book.getCheck_in()));
            fld_checkout.setText(String.valueOf(book.getCheck_out()));
            fld_adult.setText(String.valueOf(book.getPerson()));
            fld_child.setText(String.valueOf(book.getChild()));
            fld_idno.setText(String.valueOf(book.getCustomer_id()));
            fld_price.setText(String.valueOf(book.getPrice()));
            cmb_rooms.setSelectedItem(roomsManager.findByID(book.getRoom_id()).getRoomName() + " - " + hotelManager.findByID(roomsManager.findByID(book.getRoom_id()).getRoomOtelId()).getHotelName() + " - " + roomsManager.findByID(book.getRoom_id()).getRoomPrice() + " - " + roomsManager.findByID(book.getRoom_id()).getRoomPensionType());
        }


        fld_adult.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                updatePrice();
            }

            @Override
            public void removeUpdate(DocumentEvent e) {

            }


            @Override
            public void changedUpdate(DocumentEvent e) {
                updatePrice();
            }

            public void updatePrice() {
                ComboItem selectedRoom = (ComboItem) cmb_rooms.getSelectedItem();
                int person = Integer.parseInt(fld_adult.getText());
                LocalDate start = LocalDate.parse(fld_checkin.getText(), DateTimeFormatter.ofPattern("dd/MM/yyyy"));
                LocalDate end = LocalDate.parse(fld_checkout.getText(), DateTimeFormatter.ofPattern("dd/MM/yyyy"));
                long days = ChronoUnit.DAYS.between(start, end);
                fld_price.setText(String.valueOf((days * (roomsManager.findByID(selectedRoom.getKey()).getRoomPrice()) + (roomsManager.findByID(selectedRoom.getKey()).getRoomPersonPrice() * person))));
            }
        });

        fld_child.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                updatePrice();
            }

            @Override
            public void removeUpdate(DocumentEvent e) {

            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                updatePrice();
            }

            public void updatePrice() {
                ComboItem selectedRoom = (ComboItem) cmb_rooms.getSelectedItem();
                int person = Integer.parseInt(fld_adult.getText());
                int child = Integer.parseInt(fld_child.getText());
                LocalDate start = LocalDate.parse(fld_checkin.getText(), DateTimeFormatter.ofPattern("dd/MM/yyyy"));
                LocalDate end = LocalDate.parse(fld_checkout.getText(), DateTimeFormatter.ofPattern("dd/MM/yyyy"));
                long days = ChronoUnit.DAYS.between(start, end);
                //Season seasonStartDate = seasonManager.findByID();
//                if(start = season.getStartDate() && end = season.getEndDate(){
//
//                }
                Season seasonDiscount = seasonManager.findByID(roomsManager.findByID(selectedRoom.getKey()).getRoomSeasonId());
                season.getDiscount();
                fld_price.setText(String.valueOf(days * (roomsManager.findByID(selectedRoom.getKey()).getRoomPrice()) + (roomsManager.findByID(selectedRoom.getKey()).getRoomPersonPrice() * person) + (roomsManager.findByID(selectedRoom.getKey()).getRoomChildPrice() * child)));
            }
        });

        btn_save.addActionListener(e -> {
            boolean result = false;
            ComboItem selectedRoom = (ComboItem) cmb_rooms.getSelectedItem();
            book.setName(fld_name.getText());
            book.setMpno(fld_mpno.getText());
            book.setCheck_in(LocalDate.parse(fld_checkin.getText() , DateTimeFormatter.ofPattern("dd/MM/yyyy")));
            book.setCheck_out(LocalDate.parse(fld_checkout.getText(), DateTimeFormatter.ofPattern("dd/MM/yyyy")));
            book.setPerson(Integer.parseInt(fld_adult.getText()));
            book.setChild(Integer.parseInt(fld_child.getText()));
            book.setCustomer_id(Integer.parseInt(fld_idno.getText()));
            book.setPrice(Integer.parseInt(fld_price.getText()));
            book.setRoom_id(selectedRoom.getKey());
            book.setStatus("done");
            if(book.getChild() + book.getPerson() > roomsManager.findByID(book.getRoom_id()).getRoomPerson()){
                Helper.showMessage("many");

            }else{
                if (book.getId() == 0) {
                    result = bookManager.save(book);
                } else {
                    result = bookManager.update(book);
                }
                if (result) {
                    Helper.showMessage("success");
                    int stock = roomsManager.findByID(book.getRoom_id()).getRoomStock();
                    roomsManager.updateStock(book.getRoom_id(), stock - 1);
                    this.dispose();
                } else {
                    Helper.showMessage("error");
                }
            }

        });
    }

    public void cmbBoxItems() {
        for (Rooms rooms : roomsManager.findAll()) {
            cmb_rooms.addItem(new ComboItem(rooms.getId(), rooms.getRoomName() + " - " + hotelManager.findByID(rooms.getRoomOtelId()).getHotelName() + " - " + rooms.getRoomPrice() + " - " + rooms.getRoomPensionType() + " - " + rooms.getRoomPerson()));
        }
    }


    private void createUIComponents() throws ParseException {
        this.fld_checkin = new JFormattedTextField(new MaskFormatter("##/##/####"));
        this.fld_checkin.setText("01/01/2024");
        this.fld_checkout = new JFormattedTextField(new MaskFormatter("##/##/####"));
        this.fld_checkout.setText("02/01/2024");
    }


}
