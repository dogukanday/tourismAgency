import core.Db;
import view.LoginView;
import view.UserView;
import business.UserManager;

import java.sql.Connection;

public class App {
    public static void main(String[] args) {

        Connection conn = Db.getInstance();

        //LoginView loginView = new LoginView();

        UserManager userManager = new UserManager();

        UserView userView = new UserView(userManager.findByLogin("user", "1234"));

    }
}