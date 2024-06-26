package view;

import business.HotelManager;
import business.PensionManager;
import business.RoomsManager;
import core.ComboItem;
import core.Helper;
import entities.Hotel;
import entities.Pension;
import entities.Rooms;

import javax.swing.*;

public class RoomsEditView extends Layout {
    private JPanel container;
    private JLabel lbl_welcome;
    private JComboBox<ComboItem> cmb_hotel_name;
    private JTextField fld_price;
    private JTextField fld_person;
    private JTextField fld_m2;
    private JComboBox cmb_tv;
    private JComboBox cmb_mini;
    private JComboBox cmb_console;
    private JComboBox cmb_safe;
    private JComboBox cmb_project;
    private JButton btn_save;
    private JComboBox<Pension.Pensions> cmb_pension_type;
    private JLabel lbl_room_name;
    private JLabel lbl_stock;
    private JTextField fld_stock;
    private JTextField fld_name;
    private JLabel lbl_adultprice;
    private JTextField fld_adultprice;
    private JTextField fld_childprice;
    private JLabel lbl_childprice;
    private Rooms rooms;
    private RoomsManager roomsManager;
    private Hotel hotel;
    private Pension pension;
    private PensionManager pensionManager;
    private HotelManager hotelManager;

    public RoomsEditView(Rooms rooms) {
        this.add(container);
        guiInit(300, 730, "Oda Düzenle / Ekle");
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.roomsManager = new RoomsManager();
        this.rooms = new Rooms();
        this.hotel = new Hotel();
        this.pension = new Pension();
        this.pensionManager = new PensionManager();
        this.hotelManager = new HotelManager();
        cmbBoxItems();


        if (rooms.getId() != 0) {
            this.fld_price.setText(String.valueOf(rooms.getRoomPrice()));
            this.fld_person.setText(String.valueOf(rooms.getRoomPerson()));
            this.fld_m2.setText(String.valueOf(rooms.getRoomM2()));
            this.fld_adultprice.setText(String.valueOf(rooms.getRoomPersonPrice()));
            this.fld_childprice.setText(String.valueOf(rooms.getRoomChildPrice()));
            this.fld_name.setText(rooms.getRoomName());
            this.fld_stock.setText(String.valueOf(rooms.getRoomStock()));
            this.cmb_tv.setSelectedItem(rooms.isRoomTv());
            this.cmb_mini.setSelectedItem(rooms.isRoomMini());
            this.cmb_console.setSelectedItem(rooms.isRoomConsole());
            this.cmb_safe.setSelectedItem(rooms.isRoomSafe());
            this.cmb_project.setSelectedItem(rooms.isRoomProject());
            this.cmb_hotel_name.setSelectedItem(hotelManager.findByID(rooms.getRoomOtelId()).getHotelName());
            this.cmb_pension_type.setSelectedItem(rooms.getRoomPensionType());
        }

        btn_save.addActionListener(e -> {
            boolean result = false;
            ComboItem selectedHotel = (ComboItem) cmb_hotel_name.getSelectedItem();
            Pension.Pensions selectedPension = (Pension.Pensions) cmb_pension_type.getSelectedItem();
            rooms.setRoomOtelId(selectedHotel.getKey());
            rooms.setRoomPensionType((selectedPension.toString()));
            rooms.setRoomPrice(Integer.parseInt(fld_price.getText()));
            rooms.setRoomPerson(Integer.parseInt(fld_person.getText()));
            rooms.setRoomM2(Integer.parseInt(fld_m2.getText()));
            rooms.setRoomPersonPrice(Integer.parseInt(fld_adultprice.getText()));
            rooms.setRoomChildPrice(Integer.parseInt(fld_childprice.getText()));
            rooms.setRoomTv(Boolean.parseBoolean((String) cmb_tv.getSelectedItem()));
            rooms.setRoomMini(Boolean.parseBoolean((String) cmb_mini.getSelectedItem()));
            rooms.setRoomConsole(Boolean.parseBoolean((String) cmb_console.getSelectedItem()));
            rooms.setRoomSafe(Boolean.parseBoolean((String) cmb_safe.getSelectedItem()));
            rooms.setRoomProject(Boolean.parseBoolean((String) cmb_project.getSelectedItem()));
            rooms.setRoomStock(Integer.parseInt(fld_stock.getText()));
            rooms.setRoomName(fld_name.getText());
            if (rooms.getId() == 0) {
                result = roomsManager.insert(rooms);
            } else {
                roomsManager.update(rooms);
                result = true;
            }
            if (result) {
                Helper.showMessage("success");
                dispose();
            } else {
                Helper.showMessage("error");
            }


        });


    }


    public void cmbBoxItems() {
        for (Hotel hotel : hotelManager.findAll()) {
            cmb_hotel_name.addItem(new ComboItem(hotel.getId(), hotel.getHotelName()));
        }

        cmb_pension_type.setModel(new DefaultComboBoxModel<>(Pension.Pensions.values()));

        cmb_console.addItem("true");
        cmb_console.addItem("false");
        cmb_mini.addItem("true");
        cmb_mini.addItem("false");
        cmb_project.addItem("true");
        cmb_project.addItem("false");
        cmb_safe.addItem("true");
        cmb_safe.addItem("false");
        cmb_tv.addItem("true");
        cmb_tv.addItem("false");
    }


}
