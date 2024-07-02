package dao;

import core.Db;
import entities.Season;

import java.sql.*;
import java.util.ArrayList;


public class SeasonDao {
    Connection conn;

    public SeasonDao() {
        this.conn = Db.getInstance();
    }

    public boolean delete(int id) {
        String query = "DELETE FROM public.season WHERE season_id = ?";
        try {
            PreparedStatement ps = this.conn.prepareStatement(query);
            ps.setInt(1, id);
            ps.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return true;
    }

    public boolean update(Season obj) {
        String query = "UPDATE public.season SET season_strt_date = ?, season_fnsh_date = ?, season_otel_id = ?, season_discount = ? WHERE season_id = ?";
        try {
            PreparedStatement ps = this.conn.prepareStatement(query);
            ps.setDate(1, Date.valueOf(obj.getStart_date()));
            ps.setDate(2, Date.valueOf(obj.getEnd_date()));
            ps.setInt(3, obj.getOtel_id());
            ps.setInt(4, obj.getId());
            ps.setInt(5, obj.getDiscount());
            ps.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return true;
    }

    public boolean save(Season obj) {
        String query = "INSERT INTO public.season(season_strt_date, season_fnsh_date, season_otel_id, season_discount) VALUES(?, ?, ?, ?)";
        try {
            PreparedStatement ps = this.conn.prepareStatement(query);
            ps.setDate(1, Date.valueOf(obj.getStart_date()));
            ps.setDate(2, Date.valueOf(obj.getEnd_date()));
            ps.setInt(3, obj.getOtel_id());
            ps.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return true;
    }

    public ArrayList<Season> findAll() {
        return this.selectByQuery("SELECT * FROM public.season ORDER BY season_id ASC");
    }

    public ArrayList<Season> selectByQuery(String query) {
        ArrayList<Season> seasons = new ArrayList<>();
        try {
            Statement st = this.conn.createStatement();
            ResultSet rs = st.executeQuery(query);
            while (rs.next()) {
                seasons.add(this.match(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return seasons;
    }


    public Season findByID(int id) {
        Season obj = null;
        String query = "SELECT * FROM public.season WHERE season_id = ?";
        try {
            PreparedStatement ps = this.conn.prepareStatement(query);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                obj = this.match(rs);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return obj;
    }

    public Season match(ResultSet rs) throws SQLException {
        Season obj = new Season();

        obj.setId(rs.getInt("season_id"));
        obj.setStart_date(rs.getDate("season_strt_date").toLocalDate());
        obj.setEnd_date(rs.getDate("season_fnsh_date").toLocalDate());
        obj.setOtel_id(rs.getInt("season_otel_id"));
        obj.setDiscount(rs.getInt("season_discount"));

        return obj;
    }

    public Season findByHotelId(int id){
        Season obj = null;
        String query = "SELECT * FROM public.season WHERE season_otel_id = ?";
        try {
            PreparedStatement ps = this.conn.prepareStatement(query);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                obj = this.match(rs);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return obj;
    }
}
