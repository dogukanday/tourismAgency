package view;

import business.UserManager;
import entities.User;

import javax.swing.*;
import java.awt.*;

import static core.Helper.isFieldListEmpty;
import static core.Helper.showMessage;

public class LoginView extends Layout{
    private JPanel container;
    private JLabel lbl_welcome;
    private JLabel lbl_username;
    private JTextField fld_username;
    private JLabel lbl_password;
    private JTextField fld_password;
    private JButton btn_login;
    private UserManager userManager;
    private User user;

    public LoginView(){
        this.add(container);
        guiInit(400, 300, "Login");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);


        btn_login.addActionListener(e -> {
            JTextField[] fields = {fld_username, fld_password};
            String username = fld_username.getText();
            String password = fld_password.getText();
            if (isFieldListEmpty(fields)) {
                showMessage("fill");
            } else {
                userManager = new UserManager();
                User loginUser = userManager.findByLogin(fld_username.getText(), fld_password.getText());
                if (loginUser != null) {
                    showMessage("success");
                    if (loginUser.getRole().equals("admin")){
                        new AdminView(loginUser);
                        dispose();
                    } else{
                        new UserView(loginUser);
                        dispose();
                    }
                    dispose();
                } else {
                    showMessage("Invalid username or password");
                }
            }
        });


    }
}
