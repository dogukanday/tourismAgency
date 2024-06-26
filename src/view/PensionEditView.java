package view;

import business.HotelManager;
import business.PensionManager;
import core.ComboItem;
import core.Helper;
import entities.Hotel;
import entities.Pension;

import javax.swing.*;

public class PensionEditView extends Layout{
    private JPanel container;
    private JLabel lbl_welcome;
    private JLabel lbl_hotel;
    private JComboBox<ComboItem> cmb_hotel;
    private JComboBox<Pension.Pensions> cmb_penson_type;
    private JButton btn_save;
    private PensionManager pensionManager;
    private HotelManager hotelManager;
    private Hotel hotel;
    private Pension pension;

    public PensionEditView(Pension pension){
        this.add(container);
        guiInit(1000, 500, "Pansiyon Yönetim");
        this.pensionManager = new PensionManager();
        this.pension = pension;
        this.hotelManager = new HotelManager();
        this.hotel = new Hotel();
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        for (Hotel hotel : hotelManager.findAll()) {
            cmb_hotel.addItem(new ComboItem(hotel.getId(), hotel.getHotelName()));
        }
        cmb_penson_type.setModel(new DefaultComboBoxModel<>(Pension.Pensions.values()));


        if(pension.getId() != 0){

            cmb_hotel.setSelectedItem(hotelManager.findByID(pension.getHotelId()).getHotelName());

            cmb_penson_type.setSelectedItem(pension.getType());

        }

        this.btn_save.addActionListener(e -> {
            boolean result = false;
            ComboItem selectedHotel = (ComboItem) cmb_hotel.getSelectedItem();
            this.pension.setHotelId(selectedHotel.getKey());
            this.pension.setHotel(hotelManager.findByID(selectedHotel.getKey()));
            this.pension.setType((Pension.Pensions) cmb_penson_type.getSelectedItem());
            if (pension.getId() == 0) {
                result = pensionManager.save(pension);
            } else {
                result = pensionManager.update(pension);
            }
            if (result) {
                Helper.showMessage("success");
                dispose();
            } else {
                Helper.showMessage("error");
            }
        });

    }


}
