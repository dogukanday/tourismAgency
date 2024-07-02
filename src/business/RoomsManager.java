package business;

import dao.BookDao;
import dao.HotelDao;
import dao.PensionDao;
import dao.RoomsDao;
import entities.Book;
import entities.Hotel;
import entities.Rooms;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class RoomsManager {
    private final RoomsDao roomsDao;
    private final HotelDao hotelDao;
    private final BookDao bookDao = new BookDao();


    // Constructor
    public RoomsManager(){
        this.roomsDao = new RoomsDao();
        this.hotelDao = new HotelDao();

    }

    // Methods

    public ArrayList<Rooms> findAll(){
        return roomsDao.findAll();
    }

    public Rooms findByID(int id){
        return roomsDao.findByID(id);
    }

    public boolean delete(int id){
        return roomsDao.delete(id);
    }

    public Rooms update(Rooms obj){
        return roomsDao.update(obj);
    }

    public boolean insert(Rooms obj){
        return roomsDao.insert(obj);
    }

    public boolean updateStock(int id, int stock){
        return roomsDao.changeStock(id, stock);
    }

    // Method to get the data for the table

    public ArrayList<Object[]> getForTable(int size, ArrayList<Rooms> rooms){
        ArrayList<Object[]> roomRow = new ArrayList<>();
        for (Rooms room : rooms){
            Object[] row = new Object[size];
            int i = 0;
            row[i++] = room.getId();
            row[i++] = hotelDao.findByID(room.getRoomOtelId()).getHotelName();
            row[i++] = hotelDao.findByID(room.getRoomOtelId()).getHotelCity();
            row[i++] = hotelDao.findByID(room.getRoomOtelId()).getHotelDistrict();
            row[i++] = hotelDao.findByID(room.getRoomOtelId()).getHotelAdress();
            row[i++] = hotelDao.findByID(room.getRoomOtelId()).getHotelPhone();
            row[i++] = hotelDao.findByID(room.getRoomOtelId()).getHotelStars();
            row[i++] = hotelDao.findByID(room.getRoomOtelId()).isHotelPark();
            row[i++] = hotelDao.findByID(room.getRoomOtelId()).isHotelPool();
            row[i++] = hotelDao.findByID(room.getRoomOtelId()).isHotelGym();
            row[i++] = hotelDao.findByID(room.getRoomOtelId()).isHotelConcierge();
            row[i++] = hotelDao.findByID(room.getRoomOtelId()).isHotelRoomSevice();
            row[i++] = hotelDao.findByID(room.getRoomOtelId()).isHotelSpa();
            row[i++] = hotelDao.findByID(room.getRoomOtelId()).isHotelWifi();
            row[i++] = room.getRoomName();
            row[i++] = room.getRoomPensionType();
            row[i++] = room.getRoomPerson();
            row[i++] = room.getRoomM2();
            row[i++] = room.isRoomTv();
            row[i++] = room.isRoomMini();
            row[i++] = room.isRoomConsole();
            row[i++] = room.isRoomSafe();
            row[i++] = room.isRoomProject();
            row[i++] = room.getRoomStock();
            row[i++] = room.getRoomPersonPrice();
            row[i++] = room.getRoomChildPrice();
            row[i++] = room.getRoomPrice();
            roomRow.add(row);
        }
        return roomRow;
    }

    public ArrayList<Object[]> getForSearchTable(int size, ArrayList<Rooms> rooms){
        ArrayList<Object[]> roomRow = new ArrayList<>();
        for (Rooms room : rooms){
            Object[] row = new Object[size];
            int i = 0;
            row[i++] = room.getId();

            row[i++] = room.getRoomName();
            row[i++] = room.getRoomPensionType();
            row[i++] = room.getRoomPerson();
            row[i++] = room.getRoomM2();
            row[i++] = room.isRoomTv();
            row[i++] = room.isRoomMini();
            row[i++] = room.isRoomConsole();
            row[i++] = room.isRoomSafe();
            row[i++] = room.isRoomProject();
            row[i++] = room.getRoomStock();
            row[i++] = room.getRoomPersonPrice();
            row[i++] = room.getRoomChildPrice();
            row[i++] = room.getRoomPrice();
            roomRow.add(row);
        }
        return roomRow;
    }



    // Method to search for a room

    public ArrayList<Rooms> searchRoom(String start_date, String finish_date, String city, String name){
        String query = "SELECT * FROM public.room as ro LEFT JOIN public.reservation as r ON ro.room_id = r.reservation_room_id LEFT JOIN public.hotel as h ON ro.room_otel_id = h.hotel_id";

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

        ArrayList<Rooms> searchedRoomList = roomsDao.selectByQuery(query);

        System.out.println(query);

        bookOrWhere.add("'" + start_date + "' BETWEEN reservation_strt_date AND reservation_fnsh_date");
        bookOrWhere.add("'" + finish_date + "' BETWEEN reservation_strt_date AND reservation_fnsh_date");
        bookOrWhere.add("reservation_strt_date BETWEEN '" + start_date + "' AND '" + finish_date + "'");
        bookOrWhere.add("reservation_fnsh_date BETWEEN '" + start_date + "' AND '" + finish_date + "'");

        String bookOrWhereStr = String.join(" AND ", bookOrWhere);
        String bookQuery = "SELECT * FROM public.reservation WHERE " + bookOrWhereStr;

        System.out.println(bookQuery);

        ArrayList<Book> bookList = this.bookDao.selectQuery(bookQuery);
        ArrayList<Integer> busyHotelId = new ArrayList<>();
        for (Book books : bookList) {
            busyHotelId.add(books.getRoom_id());
            System.out.println(books.getRoom_id());
        }

        searchedRoomList.removeIf(rooms -> busyHotelId.contains(rooms.getId()));

        return searchedRoomList;


    }
}
