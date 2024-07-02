package view;

import business.HotelManager;
import business.SeasonManager;
import core.ComboItem;
import core.Helper;
import entities.Hotel;
import entities.Season;

import javax.swing.*;
import javax.swing.text.MaskFormatter;
import java.text.ParseException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SeasonEditView extends Layout{
    private JPanel container;
    private JLabel lbl_welcome;
    private JFormattedTextField fld_strt_date;
    private JFormattedTextField fld_fnsh_date;
    private JComboBox cmb_hotel_name;
    private JButton btn_save;
    private JLabel lbl_strt_date;
    private JLabel lbl_fnsh_date;
    private JLabel lbl_hotel_name;
    private JLabel lbl_discount;
    private JTextField fld_discount;
    private Season season;
    private SeasonManager seasonManager;
    private Hotel hotel;
    private HotelManager hotelManager;

    public SeasonEditView (Season season){
        this.add(container);
        this.guiInit(300, 600, "Sezon Ekle / Düzenle");
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.seasonManager = new SeasonManager();
        this.season = season;
        this.hotel = new Hotel();
        this.hotelManager = new HotelManager();
        cmbBoxItems();



        if(season.getId()!=0){
            this.fld_strt_date.setText(String.valueOf(season.getStart_date()));
            this.fld_fnsh_date.setText(String.valueOf(season.getEnd_date()));
            this.fld_discount.setText(String.valueOf(season.getDiscount()));
            setComboBoxSelectedById(cmb_hotel_name, season.getOtel_id());
            //this.cmb_hotel_name.setSelectedItem(new ComboItem(season.getOtel_id(), hotelManager.findByID(season.getOtel_id()).getHotelName()));
        }

        this.btn_save.addActionListener(e -> {
            if (Helper.isFieldListEmpty(new JTextField[]{fld_strt_date, fld_fnsh_date})){
                Helper.showMessage("fill");
            }else{
                boolean result = false;
                season.setStart_date(LocalDate.parse(fld_strt_date.getText(), DateTimeFormatter.ofPattern("dd/MM/yyyy")));
                season.setEnd_date(LocalDate.parse(fld_fnsh_date.getText(), DateTimeFormatter.ofPattern("dd/MM/yyyy")));
                ComboItem selectedHotel = (ComboItem) cmb_hotel_name.getSelectedItem();
                season.setOtel_id(selectedHotel.getKey());
                if (season.getId() == 0){
                    result = seasonManager.save(season);
                }else{
                    result = seasonManager.update(season);
                }

                if (result){
                    Helper.showMessage("success");
                    this.dispose();
                }else{
                    Helper.showMessage("error");
                }

            }
        });


    }

    public void cmbBoxItems(){
        for (Hotel hotel : hotelManager.findAll()){
            cmb_hotel_name.addItem(new ComboItem(hotel.getId(), hotel.getHotelName()));
        }
    }


    private void createUIComponents() throws ParseException {
        this.fld_strt_date = new JFormattedTextField(new MaskFormatter("##/##/####"));
        this.fld_strt_date.setText("01/01/2024");
        this.fld_fnsh_date = new JFormattedTextField(new MaskFormatter("##/##/####"));
        this.fld_fnsh_date.setText("02/01/2024");
    }

    public static void setComboBoxSelectedById(JComboBox<ComboItem> comboBox, Integer id) {
        for (int i = 0; i < comboBox.getItemCount(); i++) {
            if (comboBox.getItemAt(i).getKey() == (id)) {
                comboBox.setSelectedIndex(i);
                break;
            }
        }
    }
}
