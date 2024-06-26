package dao;

import core.Db;
import entities.Hotel;
import entities.Pension;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class PensionDao {
    public Connection conn;

    public PensionDao(){
        this.conn = Db.getInstance();
    }

    public Pension getById(int id){
        Pension obj = null;
        String query = "SELECT * FROM public.pension_type WHERE pension_id = ?";
        try {
            PreparedStatement ps = this.conn.prepareStatement(query);
            ps.setInt(1,id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()){
                obj = this.match(rs);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return obj;
    }

    public Pension match(ResultSet rs) throws SQLException{
        Pension obj = new Pension();

        obj.setId(rs.getInt("pension_id"));
        obj.setHotelId(rs.getInt("pension_otel_id"));
        obj.setType(Pension.Pensions.valueOf(rs.getString("pension_type")));

        return obj;
    }

    public boolean delete(int id) {
        String query = "DELETE FROM public.pension_type WHERE pension_id = ?";
        try {
            PreparedStatement st = this.conn.prepareStatement(query);
            st.setInt(1, id);
            return st.executeUpdate() != -1;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return true;
    }

    public Pension update(Pension obj){
        String query = "UPDATE public.pension_type SET pension_type = ? WHERE pension_id = ?";
        try {
            PreparedStatement st = this.conn.prepareStatement(query);
            st.setString(1, obj.getType().toString());
            st.setInt(2, obj.getId());
            st.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();}
        return obj;
    }

    public boolean save(Pension obj){
        String query = "INSERT INTO public.pension_type (pension_type, pension_otel_id) VALUES (?,?)";
        try {
            PreparedStatement st = this.conn.prepareStatement(query);
            st.setString(1, obj.getType().toString());
            st.setInt(2,obj.getHotel().getId());
            st.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return true;
    }

    public Pension findByHotelId(int id){
        Pension obj = null;
        String query = "SELECT * FROM public.pension_type WHERE hotel_id = ?";
        try {
            PreparedStatement ps = this.conn.prepareStatement(query);
            ps.setInt(1,id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()){
                obj = this.match(rs);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return obj;
    }

    public ArrayList<Pension> selectByQuery(String query){
        ArrayList<Pension> pensions = new ArrayList<>();
        try {
            PreparedStatement st = this.conn.prepareStatement(query);
            ResultSet rs = st.executeQuery();
            while (rs.next()){
                pensions.add(this.match(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return pensions;
    }

    public ArrayList<Pension> findAll(){
        return this.selectByQuery("SELECT * FROM public.pension_type ORDER BY pension_otel_id ASC");
    }
}
