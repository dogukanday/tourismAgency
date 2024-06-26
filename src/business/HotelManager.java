package business;

import dao.HotelDao;
import entities.Hotel;

import java.util.ArrayList;

public class HotelManager {
    private final HotelDao hotelDao;

    public HotelManager(){
        this.hotelDao = new HotelDao();
    }

    public ArrayList<Hotel> findAll(){
        return hotelDao.findAll();
    }

    public ArrayList<Object[]> getForTable(int size, ArrayList<Hotel> hotels){
        ArrayList<Object[]> hotelRow = new ArrayList<>();
        for (Hotel hotel : hotels){
            Object[] row = new Object[size];
            int i = 0;
            row[i++] = hotel.getId();
            row[i++] = hotel.getHotelName();
            row[i++] = hotel.getHotelAdress();
            row[i++] = hotel.getHotelPhone();
            row[i++] = hotel.getHotelEmail();
            row[i++] = hotel.getHotelCity();
            row[i++] = hotel.getHotelDistrict();
            row[i++] = hotel.getHotelStars();
            row[i++] = hotel.isHotelPark();
            row[i++] = hotel.isHotelPool();
            row[i++] = hotel.isHotelGym();
            row[i++] = hotel.isHotelConcierge();
            row[i++] = hotel.isHotelSpa();
            row[i++] = hotel.isHotelRoomSevice();
            row[i++] = hotel.isHotelWifi();
            hotelRow.add(row);
        }
        return hotelRow;
    }

    public Hotel findByID(int id){
        return hotelDao.findByID(id);
    }

    public boolean save(Hotel hotel){
        if (hotel.getId() == 0) {
            hotelDao.save(hotel);
        }else {
            hotelDao.update(hotel);
        }
        return true;
    }

    public boolean update(Hotel hotel){
        if (hotel.getId() != 0) {
            hotelDao.update(hotel);
        }
        return true;
    }

    public boolean delete(int id){
        hotelDao.delete(id);
        return true;
    }

}
