package view;

import business.HotelManager;
import core.Helper;
import entities.Hotel;

import javax.swing.*;

import static core.Helper.showMessage;

public class HotelEditView extends Layout{
    private JPanel container;
    private JLabel fld_welcome;
    private JLabel fld_hotelname;
    private JTextField fld_name;
    private JTextField fld_adress;
    private JTextField fld_phone;
    private JTextField fld_mail;
    private JComboBox cmb_park;
    private JComboBox cmb_wifi;
    private JComboBox cmb_pool;
    private JComboBox cmb_gym;
    private JComboBox cmb_con;
    private JComboBox cmb_spa;
    private JComboBox cmb_roomservice;
    private JLabel lbl_adress;
    private JLabel lbl_phone;
    private JLabel lbl_mail;
    private JTextField fld_stars;
    private JLabel lbl_stars;
    private JLabel lbl_park;
    private JLabel lbl_wifi;
    private JLabel lbl_pool;
    private JLabel lbl_gym;
    private JLabel lbl_con;
    private JLabel lbl_spa;
    private JLabel lbl_roomservice;
    private JButton btn_save;
    private JLabel lbl_city;
    private JTextField fld_city;
    private JLabel lbl_district;
    private JTextField fld_district;
    private JComboBox cmb_pension;
    private Hotel hotel;
    private HotelManager hotelManager;



    public HotelEditView(Hotel hotel){
        this.add(container);
        guiInit(300, 700, "Otel Düzenle / Ekle");
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.hotelManager = new HotelManager();
        this.hotel = hotel;
        cmbBoxItems();




        if(hotel.getId() != 0){
            fld_name.setText(hotel.getHotelName());
            fld_adress.setText(hotel.getHotelAdress());
            fld_phone.setText(hotel.getHotelPhone());
            fld_mail.setText(hotel.getHotelEmail());
            fld_stars.setText(String.valueOf(hotel.getHotelStars()));
            fld_city.setText(hotel.getHotelCity());
            fld_district.setText(hotel.getHotelDistrict());
            cmb_park.setSelectedItem(Boolean.toString(hotel.isHotelPark()));
            cmb_pool.setSelectedItem(Boolean.toString(hotel.isHotelPool()));
            cmb_wifi.setSelectedItem(Boolean.toString(hotel.isHotelWifi()));
            cmb_gym.setSelectedItem(Boolean.toString(hotel.isHotelGym()));
            cmb_spa.setSelectedItem(Boolean.toString(hotel.isHotelSpa()));
            cmb_con.setSelectedItem(Boolean.toString(hotel.isHotelConcierge()));
            cmb_roomservice.setSelectedItem(Boolean.toString(hotel.isHotelRoomSevice()));
        }

        this.btn_save.addActionListener(e -> {
            if (Helper.isFieldListEmpty(new JTextField[]{fld_name,fld_adress, fld_phone,fld_mail,fld_stars})) {
                showMessage("fill");
            } else {
                hotel.setHotelName(fld_name.getText());
                hotel.setHotelAdress(fld_adress.getText());
                hotel.setHotelPhone(fld_phone.getText());
                hotel.setHotelEmail(fld_mail.getText());
                hotel.setHotelCity(fld_city.getText());
                hotel.setHotelDistrict(fld_district.getText());
                hotel.setHotelStars(Integer.parseInt(fld_stars.getText()));
                hotel.setHotelPark(Boolean.parseBoolean((String) cmb_park.getSelectedItem()));
                hotel.setHotelPool(Boolean.parseBoolean((String) cmb_pool.getSelectedItem()));
                hotel.setHotelWifi(Boolean.parseBoolean((String) cmb_wifi.getSelectedItem()));
                hotel.setHotelGym(Boolean.parseBoolean((String) cmb_gym.getSelectedItem()));
                hotel.setHotelConcierge(Boolean.parseBoolean((String) cmb_con.getSelectedItem()));
                hotel.setHotelSpa(Boolean.parseBoolean((String) cmb_spa.getSelectedItem()));
                hotel.setHotelRoomSevice(Boolean.parseBoolean((String) cmb_roomservice.getSelectedItem()));
                hotelManager.save(hotel);
                showMessage("success");
                dispose();
            }
        });

        }

        public void cmbBoxItems(){
            cmb_park.addItem("true");
            cmb_park.addItem("false");
            cmb_pool.addItem("true");
            cmb_pool.addItem("false");
            cmb_wifi.addItem("true");
            cmb_wifi.addItem("false");
            cmb_gym.addItem("true");
            cmb_gym.addItem("false");
            cmb_con.addItem("true");
            cmb_con.addItem("false");
            cmb_spa.addItem("true");
            cmb_spa.addItem("false");
            cmb_roomservice.addItem("true");
            cmb_roomservice.addItem("false");


        }

    }
