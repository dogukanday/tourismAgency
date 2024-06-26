package view;

import business.UserManager;
import core.ComboItem;
import core.Helper;
import entities.User;

import javax.swing.*;

public class UserEditerView extends Layout {
    private JLabel lbl_welcome;
    private JTextField fld_username;
    private JLabel lbl_username;
    private JLabel lbl_password;
    private JPasswordField fld_password;
    private JLabel lbl_role;
    private JComboBox cmb_role;
    private JButton btn_save;
    private JPanel container;
    private User user;
    private UserManager userManager;

    public UserEditerView(User user){
        this.add(container);
        this.guiInit(300, 500, "User Editer");
        this.userManager = new UserManager();
        this.user = user;
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);




        lbl_welcome.setText("Hoş geldiniz, " + user.getUsername());

        if(user.getUser_id() != 0){
            fld_username.setText(user.getUsername());
            fld_password.setText(user.getPassword());
            cmb_role.setSelectedItem(user.getRole());
        }


        cmb_role.addItem(new ComboItem(0,"admin"));
        cmb_role.addItem(new ComboItem(1,"user"));

        this.btn_save.addActionListener(e -> {
            if (Helper.isFieldListEmpty(new JTextField[]{fld_username,fld_password})) {
                Helper.showMessage("error");
            } else {
                user.setUsername(fld_username.getText());
                user.setPassword(fld_password.getText());
                user.setRole(((ComboItem)cmb_role.getSelectedItem()).getValue().toString());
                userManager.save(user);
                Helper.showMessage("success");
                dispose();
            }
        });


    }
}
