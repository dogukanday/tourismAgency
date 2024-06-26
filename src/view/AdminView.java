package view;

import business.UserManager;
import core.Helper;
import entities.User;

import javax.swing.*;
import javax.swing.table.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;

public class AdminView extends Layout{
    private JPanel container;
    private JTabbedPane tab_admin;
    private JLabel lbl_welcome;
    private JPanel pnl_users;
    private JScrollPane scrl_users;
    private JTable tbl_users;
    private JLabel lbl_userwelcome;
    private JPanel pnl_search;
    private JLabel lbl_s_role;
    private JComboBox cmb_s_role;
    private JButton btn_search;
    private JButton btn_s_clear;
    private JPopupMenu users_menu;
    private DefaultTableModel tmdl_users;
    private Object[] col_users;
    private UserManager userManager;
    private User user;


    public AdminView(User user){
        this.add(container);
        guiInit(1000, 500, "Admin");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.userManager = new UserManager();
        this.user = new User();
        this.tmdl_users = new DefaultTableModel();
        if (user == null){
            dispose();
        }

        lbl_userwelcome.setText("Hoş geldiniz, " + user.getUsername());
        setUsersTable();
        selectUser();
        loadUserFilter();





    }

    public void setUsersTable(){
        col_users = new Object[]{"ID", "Kullanıcı Adı", "Şifre", "Yetki"};
        ArrayList<Object[]> users = this.userManager.getForTable(col_users.length, this.userManager.findAllByQuery());
        createTable(tmdl_users, tbl_users, col_users, users);
        tableRowSelected(tbl_users);
    }

    public void selectUser(){
       tableRowSelected(tbl_users);

       this.users_menu = new JPopupMenu();

       users_menu.add("Yeni Kullanıcı").addActionListener(e -> {
        UserEditerView userView = new UserEditerView(new User());
        userView.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosed(WindowEvent e) {
                setUsersTable();
            }
        });
       });

       users_menu.add("Kullanıcıyı Sil").addActionListener(e -> {
              int selectedRow = Helper.getTableSelectedRow(tbl_users,0);
              if(selectedRow == -1){
                Helper.showMessage("error");
              }else{

                  if(Helper.confirm("sure")){
                      userManager.delete(selectedRow);
                  }
                  setUsersTable();
              }
       });

       users_menu.add("Kullanıcıyı Güncelle").addActionListener(e -> {
           int selectedRow = Helper.getTableSelectedRow(tbl_users,0);
           if(selectedRow == -1){
               Helper.showMessage("error");
           }else{
               UserEditerView userView = new UserEditerView(userManager.findByID(selectedRow));
               userView.addWindowListener(new WindowAdapter() {
                   @Override
                   public void windowClosed(WindowEvent e) {
                       setUsersTable();
                   }
               });
           }
       });


        this.tbl_users.setComponentPopupMenu(users_menu);


        btn_search.addActionListener(e -> {
            String role = cmb_s_role.getSelectedItem().toString();
            ArrayList<Object[]> users = this.userManager.getForTable(col_users.length, this.userManager.findByRole(role));
            createTable(tmdl_users, tbl_users, col_users, users);
            tableRowSelected(tbl_users);
        });

        btn_s_clear.addActionListener(e -> {
            cmb_s_role.setSelectedItem(null);
            setUsersTable();
        });

    }

    public void loadUserFilter(){
        cmb_s_role.addItem("admin");
        cmb_s_role.addItem("user");

        cmb_s_role.setSelectedItem(null);


    }


}
