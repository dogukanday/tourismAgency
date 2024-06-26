package dao;

import core.Db;
import entities.Book;

import java.sql.*;
import java.util.ArrayList;

public class BookDao {
    Connection conn;

    public BookDao(){
        this.conn = Db.getInstance();
    }

    public ArrayList<Book> findAll(){
        ArrayList<Book> list = new ArrayList<Book>();
        String query = "SELECT * FROM public.reservation";
        try {
            PreparedStatement ps = this.conn.prepareStatement(query);
            ResultSet rs = ps.executeQuery();
            while (rs.next()){
                list.add(this.match(rs));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    public Book findByID(int id){
        Book obj = null;
        String query = "SELECT * FROM public.reservation WHERE reservation_id = ?";
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

    public boolean save(Book obj){
        String query = "INSERT INTO public.reservation (reservation_room_id, reservation_id_no, reservation_name, reservation_mpno, reservation_strt_date, reservation_fnsh_date, reservation_adult_number, reservation_child_number, reservation_price, reservation_status) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        try {
            PreparedStatement ps = this.conn.prepareStatement(query);
            ps.setInt(1, obj.getRoom_id());
            ps.setInt(2, obj.getCustomer_id());
            ps.setString(3, obj.getName());
            ps.setString(4, obj.getMpno());
            ps.setDate(5, Date.valueOf(obj.getCheck_in()));
            ps.setDate(6, Date.valueOf(obj.getCheck_out()));
            ps.setInt(7, obj.getPerson());
            ps.setInt(8, obj.getChild());
            ps.setInt(9, obj.getPrice());
            ps.setString(10, obj.getStatus());
            return ps.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
    }
        return true;
    }

    public boolean update(Book obj){
        String query = "UPDATE public.reservation SET reservation_room_id = ?, reservation_id_no = ?, reservation_name = ?, reservation_mpno = ?, reservation_strt_date = ?, reservation_fnsh_date = ?, reservation_adult_number = ?, reservation_child_number = ?, reservation_price = ?, reservation_status = ? WHERE reservation_id = ?";
        try {
            PreparedStatement ps = this.conn.prepareStatement(query);
            ps.setInt(1, obj.getRoom_id());
            ps.setInt(2, obj.getCustomer_id());
            ps.setString(3, obj.getName());
            ps.setString(4, obj.getMpno());
            ps.setDate(5, Date.valueOf(obj.getCheck_in()));
            ps.setDate(6, Date.valueOf(obj.getCheck_out()));
            ps.setInt(7, obj.getPerson());
            ps.setInt(8, obj.getChild());
            ps.setInt(9, obj.getPrice());
            ps.setString(10, obj.getStatus());
            ps.setInt(11, obj.getId());
            return ps.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return true;
    }

    public boolean delete(int id){
        String query = "DELETE FROM public.reservation WHERE reservation_id = ?";
        try {
            PreparedStatement ps = this.conn.prepareStatement(query);
            ps.setInt(1, id);
            return ps.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return true;
    }


    public ArrayList<Book> selectByQuery(){
        ArrayList<Book> list = new ArrayList<Book>();
        String query = "SELECT * FROM public.reservation";
        try {
            PreparedStatement ps = this.conn.prepareStatement(query);
            ResultSet rs = ps.executeQuery();
            while (rs.next()){
                list.add(this.match(rs));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    public Book match(ResultSet rs) throws SQLException {
        Book obj = new Book();

        obj.setId(rs.getInt("reservation_id"));
        obj.setRoom_id(rs.getInt("reservation_room_id"));
        obj.setCustomer_id(rs.getInt("reservation_id_no"));
        obj.setName(rs.getString("reservation_name"));
        obj.setMpno(rs.getString("reservation_mpno"));
        obj.setCheck_in(rs.getDate("reservation_strt_date").toLocalDate());
        obj.setCheck_out(rs.getDate("reservation_fnsh_date").toLocalDate());
        obj.setPerson(rs.getInt("reservation_adult_number"));
        obj.setChild(rs.getInt("reservation_child_number"));
        obj.setPrice(rs.getInt("reservation_price"));
        obj.setStatus(rs.getString("reservation_status"));

        return obj;
    }
}
