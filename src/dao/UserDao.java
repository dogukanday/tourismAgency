package dao;

import java.sql.*;
import java.util.ArrayList;

import core.Db;
import entities.User;

public class UserDao {
    Connection conn;

    public UserDao(){
        this.conn = Db.getInstance();
    }

    public User findByID(int id){
        User obj = null;
        String query = "SELECT * FROM public.user WHERE user_id = ?";
        try {
            PreparedStatement ps = this.conn.prepareStatement(query);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()){
                obj = this.match(rs);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return obj;
    }

    public User findByLogin(String username, String password){
        User obj = null;
        String query = "SELECT * FROM public.user WHERE user_name = ? AND user_password = ?";
        try {
            PreparedStatement ps = this.conn.prepareStatement(query);
            ps.setString(1, username);
            ps.setString(2, password);
            ResultSet rs = ps.executeQuery();
            if (rs.next()){
                obj = this.match(rs);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return obj;
    }

    public User match(ResultSet rs) throws SQLException {
        User obj = new User();

        obj.setUser_id(rs.getInt("user_id"));
        obj.setUsername(rs.getString("user_name"));
        obj.setPassword(rs.getString("user_password"));
        obj.setRole(rs.getString("user_role"));

        return obj;
    }

    public User findAll(){
        User obj = null;
        String query = "SELECT * FROM public.user";
        try {
            PreparedStatement ps = this.conn.prepareStatement(query);
            ResultSet rs = ps.executeQuery();
            if (rs.next()){
                obj = this.match(rs);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return obj;
    }

    public ArrayList<User> findAllByQuery(){
        return this.selectByQuery("SELECT * FROM public.user");
    }

    public ArrayList<User> selectByQuery(String query){
        ArrayList<User> models = new ArrayList<>();
        try {
            Statement st = this.conn.createStatement();
            ResultSet rs = st.executeQuery(query);
            while (rs.next()){
                models.add(this.match(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return models;
    }

    public boolean delete(int id){
        String query = "DELETE FROM public.user WHERE user_id = ?";
        try {
            PreparedStatement ps = this.conn.prepareStatement(query);
            ps.setInt(1, id);
            return ps.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean save(User obj){
        String query = "INSERT INTO public.user (user_name, user_password, user_role) VALUES (?, ?, ?)";
        try {
            PreparedStatement ps = this.conn.prepareStatement(query);
            ps.setString(1, obj.getUsername());
            ps.setString(2, obj.getPassword());
            ps.setString(3, obj.getRole());
            return ps.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean update(User obj){
        String query = "UPDATE public.user SET user_name = ?, user_password = ?, user_role = ? WHERE user_id = ?";
        try {
            PreparedStatement ps = this.conn.prepareStatement(query);
            ps.setString(1, obj.getUsername());
            ps.setString(2, obj.getPassword());
            ps.setString(3, obj.getRole());
            ps.setInt(4, obj.getUser_id());
            return ps.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public ArrayList<User> findByRole(String role){
        return this.selectByQuery("SELECT * FROM public.user WHERE user_role = '" + role + "'");
    }
}
