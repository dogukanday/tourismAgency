package business;

import dao.HotelDao;
import dao.SeasonDao;
import entities.Season;

import java.util.ArrayList;

public class SeasonManager {
    private final SeasonDao seasonDao;
    private final HotelDao hotelDao;

    // Constructor
    public SeasonManager() {
        this.seasonDao = new SeasonDao();
        this.hotelDao = new HotelDao();
    }
    // Methods
    public boolean delete(int id) {
        return this.seasonDao.delete(id);
    }

    public boolean update(Season obj) {
        return this.seasonDao.update(obj);
    }

    public boolean save(Season obj) {
        return this.seasonDao.save(obj);
    }

    public ArrayList<Season> findAll() {
        return this.seasonDao.findAll();
    }

    public Season findByID(int id) {
        return this.seasonDao.findByID(id);
    }

    // This method is used to get the data for the table
    public ArrayList<Object[]> getForTable(int size, ArrayList<Season> seasons) {
        ArrayList<Object[]> seasonRow = new ArrayList<>();
        for (Season season : seasons) {
            Object[] row = new Object[size];
            int i = 0;
            row[i++] = season.getId();
            row[i++] = season.getStart_date();
            row[i++] = season.getEnd_date();
            row[i++] = this.hotelDao.findByID(season.getOtel_id()).getHotelName();
            row[i++] = season.getDiscount();
            seasonRow.add(row);
        }
        return seasonRow;
    }

    public Season findByHotelID(int id) {
        return this.seasonDao.findByHotelId(id);
    }
}
