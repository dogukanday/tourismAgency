package dao;

import core.Db;
import entities.Pension;
import entities.Rooms;

import java.sql.*;
import java.util.ArrayList;

public class RoomsDao {
    Connection conn;

    public RoomsDao(){
        this.conn = Db.getInstance();
    }

    public ArrayList<Rooms> selectByQuery(String query){
        ArrayList<Rooms> rooms = new ArrayList<>();
        try {
            PreparedStatement st = this.conn.prepareStatement(query);
            ResultSet rs = st.executeQuery();
            while (rs.next()){
                rooms.add(this.match(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return rooms;
    }

    public Rooms match(ResultSet rs) throws SQLException {
        Rooms obj = new Rooms();

        obj.setId(rs.getInt("room_id"));
        obj.setRoomOtelId(rs.getInt("room_otel_id"));
        obj.setRoomSeasonId(rs.getInt("room_season_id"));
        obj.setRoomPensionType(rs.getString("room_pension_type"));
        obj.setRoomPrice(rs.getInt("room_price"));
        obj.setRoomStock(rs.getInt("room_stock"));
        obj.setRoomName(rs.getString("room_name"));
        obj.setRoomPerson(rs.getInt("room_person"));
        obj.setRoomM2(rs.getInt("room_msquare"));
        obj.setRoomTv(rs.getBoolean("room_tv"));
        obj.setRoomMini(rs.getBoolean("room_mini"));
        obj.setRoomConsole(rs.getBoolean("room_console"));
        obj.setRoomSafe(rs.getBoolean("room_safe"));
        obj.setRoomProject(rs.getBoolean("room_project"));
        obj.setRoomChildPrice(rs.getInt("room_child_price"));
        obj.setRoomPersonPrice(rs.getInt("room_adult_price"));

        return obj;
    }

    public ArrayList<Rooms> findAll(){
        return this.selectByQuery("SELECT * FROM public.room ORDER BY room_id ASC");
    }

    public Rooms findByID(int id){
        Rooms obj = null;
        String query = "SELECT * FROM public.room WHERE room_id = ?";
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

    public boolean insert(Rooms obj){
        String query = "INSERT INTO public.room(room_otel_id, room_season_id, room_pension_type, room_person, room_price, room_msquare, room_tv, room_mini, room_console, room_safe, room_project, room_stock, room_name, room_adult_price, room_child_price) VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
        try {
            PreparedStatement ps = this.conn.prepareStatement(query);
            ps.setInt(1, obj.getRoomOtelId());
            ps.setInt(2, obj.getRoomSeasonId());
            ps.setString(3, obj.getRoomPensionType());
            ps.setInt(4, obj.getRoomPerson());
            ps.setInt(5, obj.getRoomPrice());
            ps.setInt(6, obj.getRoomM2());
            ps.setBoolean(7, obj.isRoomTv());
            ps.setBoolean(8, obj.isRoomMini());
            ps.setBoolean(9, obj.isRoomConsole());
            ps.setBoolean(10, obj.isRoomSafe());
            ps.setBoolean(11, obj.isRoomProject());
            ps.setInt(12, obj.getRoomStock());
            ps.setString(13, obj.getRoomName());
            ps.setInt(14, obj.getRoomPersonPrice());
            ps.setInt(15, obj.getRoomChildPrice());
            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return true;
    }

    public boolean changeStock(int id, int stock){
        String query = "UPDATE public.room SET room_stock = ? WHERE room_id = ?";
        try {
            PreparedStatement ps = this.conn.prepareStatement(query);
            ps.setInt(1, stock);
            ps.setInt(2, id);
            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return true;
    }

    public boolean delete(int id){
        String query = "DELETE FROM public.room WHERE room_id = ?";
        try {
            PreparedStatement ps = this.conn.prepareStatement(query);
            ps.setInt(1, id);
            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return true;
    }

    public Rooms update(Rooms obj) {
        String query = "UPDATE public.room SET room_otel_id = ?, room_season_id = ?, room_pension_type = ?, room_person = ?, room_price = ?, room_msquare = ?, room_tv = ?, room_mini = ?, room_console = ?, room_safe = ?, room_project = ?, room_stock = ?, room_name = ?, room_adult_price = ?, room_child_price = ? WHERE room_id = ?";
        try {
            PreparedStatement ps = this.conn.prepareStatement(query);
            ps.setInt(1, obj.getRoomOtelId());
            ps.setInt(2, obj.getRoomSeasonId());
            ps.setString(3, obj.getRoomPensionType());
            ps.setInt(4, obj.getRoomPerson());
            ps.setInt(5, obj.getRoomPrice());
            ps.setInt(6, obj.getRoomM2());
            ps.setBoolean(7, obj.isRoomTv());
            ps.setBoolean(8, obj.isRoomMini());
            ps.setBoolean(9, obj.isRoomConsole());
            ps.setBoolean(10, obj.isRoomSafe());
            ps.setBoolean(11, obj.isRoomProject());
            ps.setInt(12, obj.getId());
            ps.setInt(13, obj.getRoomStock());
            ps.setString(14, obj.getRoomName());
            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return obj;
    }
}
