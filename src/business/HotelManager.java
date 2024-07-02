package business;

import dao.BookDao;
import dao.HotelDao;
import entities.Book;
import entities.Hotel;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class HotelManager {
    private final HotelDao hotelDao;
    private final BookDao bookDao = new BookDao();

    // Constructor
    public HotelManager(){
        this.hotelDao = new HotelDao();
    }


    // Methods
    public ArrayList<Hotel> findAll(){
        return hotelDao.findAll();
    }


    // Method to get the data for the table
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


    // Method to search for a hotel
    public ArrayList<Hotel> searchHotel(String start_date, String finish_date, String city, String name){
        String query = "SELECT * FROM public.hotel as h LEFT JOIN public.reservation as r ON h.hotel_id = r.reservation_hotel_id";

        ArrayList<String> where = new ArrayList<>();
        ArrayList<String> bookOrWhere = new ArrayList<>();

        start_date = LocalDate.parse(start_date, DateTimeFormatter.ofPattern("dd/MM/yyyy")).toString();
        finish_date = LocalDate.parse(finish_date, DateTimeFormatter.ofPattern("dd/MM/yyyy")).toString();

        if (!city.equals("")) {
            where.add("h.hotel_city = '" + city + "'");
        }

        if (!name.equals("")) {
            where.add("h.hotel_name = '" + name + "'");
        }

        String whereStr = String.join(" AND ", where);

        if (whereStr.length() > 0) {
            query += " WHERE " + whereStr;
        }

        ArrayList<Hotel> hotels = hotelDao.selectByQuery(query);

        bookOrWhere.add("'" + start_date + "' BETWEEN reservation_strt_date AND reservation_fnsh_date");
        bookOrWhere.add("'" + finish_date + "' BETWEEN reservation_strt_date AND reservation_fnsh_date");
        bookOrWhere.add("reservation_strt_date BETWEEN '" + start_date + "' AND '" + finish_date + "'");
        bookOrWhere.add("reservation_fnsh_date BETWEEN '" + start_date + "' AND '" + finish_date + "'");

        String bookOrWhereStr = String.join(" AND ", bookOrWhere);
        String bookQuery = "SELECT * FROM public.reservation WHERE " + bookOrWhereStr;

        ArrayList<Book> bookList = this.bookDao.selectQuery(bookQuery);
        ArrayList<Integer> busyHotelId = new ArrayList<>();
        for (Book book : bookList) {
            busyHotelId.add(book.getHotel_id());
        }

        hotels.removeIf(hotel -> busyHotelId.contains(hotel.getId()));

        return hotels;


    }

}
