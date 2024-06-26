package dao;

import core.Db;
import entities.Hotel;

import java.sql.*;
import java.util.ArrayList;

public class HotelDao {
    public Connection conn;
    private PensionDao pensionDao;


    public HotelDao(){
        this.conn = Db.getInstance();
        this.pensionDao = new PensionDao();
    }

    public ArrayList<Hotel> findAll(){
        return this.selectByQuery("SELECT * FROM public.hotel LEFT JOIN public.pension_type ON hotel_id = pension_otel_id ORDER BY hotel_id ASC");
    }

    public Hotel findByID(int id){
        Hotel obj = new Hotel();
        String query = "SELECT * FROM public.hotel WHERE hotel_id = ?";
        try {
            PreparedStatement ps = this.conn.prepareStatement(query);
            ps.setInt(1,id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()){
                obj = this.match(rs);
            }else {
                System.out.println("No hotel found with id: " + id);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return obj;
    }

    public ArrayList<Hotel> selectByQuery(String query){
        ArrayList<Hotel> hotels = new ArrayList<>();
        try {
            Statement st = this.conn.createStatement();
            ResultSet rs = st.executeQuery(query);
            while (rs.next()){
                hotels.add(this.match(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return hotels;
    }

    public Hotel match(ResultSet rs) throws SQLException {
        Hotel obj = new Hotel();

        obj.setId(rs.getInt("hotel_id"));
        obj.setHotelName(rs.getString("hotel_name"));
        obj.setHotelAdress(rs.getString("hotel_adress"));
        obj.setHotelPhone(rs.getString("hotel_phone"));
        obj.setHotelEmail(rs.getString("hotel_mail"));
        obj.setHotelCity(rs.getString("hotel_city"));
        obj.setHotelDistrict(rs.getString("hotel_district"));
        obj.setHotelStars(rs.getInt("hotel_stars"));
        obj.setHotelPark(rs.getBoolean("hotel_park"));
        obj.setHotelPool(rs.getBoolean("hotel_pool"));
        obj.setHotelWifi(rs.getBoolean("hotel_wifi"));
        obj.setHotelGym(rs.getBoolean("hotel_fitness"));
        obj.setHotelSpa(rs.getBoolean("hotel_spa"));
        obj.setHotelConcierge(rs.getBoolean("hotel_concierge"));
        obj.setHotelRoomSevice(rs.getBoolean("hotel_roomservice"));

        return obj;
    }

    public boolean save(Hotel obj){
        String query = "INSERT INTO public.hotel (hotel_name, hotel_adress, hotel_phone, hotel_mail, hotel_city, hotel_district, hotel_stars, hotel_park, hotel_pool, hotel_wifi, hotel_fitness, hotel_spa, hotel_concierge, hotel_roomservice) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
        try {
            PreparedStatement ps = this.conn.prepareStatement(query);
            ps.setString(1, obj.getHotelName());
            ps.setString(2, obj.getHotelAdress());
            ps.setString(3, obj.getHotelPhone());
            ps.setString(4, obj.getHotelEmail());
            ps.setString(5, obj.getHotelCity());
            ps.setString(6, obj.getHotelDistrict());
            ps.setInt(7, obj.getHotelStars());
            ps.setBoolean(8, obj.isHotelPark());
            ps.setBoolean(9, obj.isHotelPool());
            ps.setBoolean(10, obj.isHotelWifi());
            ps.setBoolean(11, obj.isHotelGym());
            ps.setBoolean(12, obj.isHotelSpa());
            ps.setBoolean(13, obj.isHotelConcierge());
            ps.setBoolean(14, obj.isHotelRoomSevice());
            return ps.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return true;
    }

    public boolean delete(int id){
        String query = "DELETE FROM public.hotel WHERE hotel_id = ?";
        try {
            PreparedStatement ps = this.conn.prepareStatement(query);
            ps.setInt(1, id);
            return ps.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean update(Hotel obj){
        String query = "UPDATE public.hotel SET hotel_name = ?, hotel_adress = ?, hotel_phone = ?, hotel_mail = ?, hotel_city = ?, hotel_district = ?, hotel_stars = ?, hotel_park = ?, hotel_pool = ?, hotel_wifi = ?, hotel_fitness = ?, hotel_spa = ?, hotel_concierge = ?, hotel_roomservice = ? WHERE hotel_id = ?";
        try {
            PreparedStatement ps = this.conn.prepareStatement(query);
            ps.setString(1, obj.getHotelName());
            ps.setString(2, obj.getHotelAdress());
            ps.setString(3, obj.getHotelPhone());
            ps.setString(4, obj.getHotelEmail());
            ps.setString(5, obj.getHotelCity());
            ps.setString(6, obj.getHotelDistrict());
            ps.setInt(7, obj.getHotelStars());
            ps.setBoolean(8, obj.isHotelPark());
            ps.setBoolean(9, obj.isHotelPool());
            ps.setBoolean(10, obj.isHotelWifi());
            ps.setBoolean(11, obj.isHotelGym());
            ps.setBoolean(12, obj.isHotelSpa());
            ps.setBoolean(13, obj.isHotelConcierge());
            ps.setBoolean(14, obj.isHotelRoomSevice());
            ps.setInt(15, obj.getId());
            return ps.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return true;
    }





}

