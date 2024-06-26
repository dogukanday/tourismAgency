package business;

import dao.UserDao;
import entities.User;

import java.util.ArrayList;

public class UserManager {
    private final UserDao userDao;

    public UserManager() {
        this.userDao = new UserDao();
    }

    public User findByLogin(String username, String password) {
        return userDao.findByLogin(username, password);
    }

    public User findAll() {
        return userDao.findAll();
    }

    public ArrayList<User> findAllByQuery() {
        return userDao.findAllByQuery();
    }

    public ArrayList<Object[]> getForTable(int size, ArrayList<User> users) {
        ArrayList<Object[]> userRow = new ArrayList<>();
        for (User user : users) {
            Object[] row = new Object[size];
            int i = 0;
            row[i++] = user.getUser_id();
            row[i++] = user.getUsername();
            row[i++] = user.getPassword();
            row[i++] = user.getRole();
            userRow.add(row);
        }
        return userRow;
    }

    public User findByID(int id) {
        return userDao.findByID(id);
    }

    public void delete(int id) {
        userDao.delete(id);
    }

    public void save(User user) {
        if (user.getUser_id() == 0) {
            userDao.save(user);
        } else {
            userDao.update(user);
        }
    }

   public ArrayList<User> findByRole(String role) {
        return userDao.findByRole(role);
    }
}
