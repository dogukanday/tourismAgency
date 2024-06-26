package business;

import dao.HotelDao;
import dao.PensionDao;
import dao.RoomsDao;
import entities.Rooms;

import java.util.ArrayList;

public class RoomsManager {
    private final RoomsDao roomsDao;
    private final HotelDao hotelDao;


    public RoomsManager(){
        this.roomsDao = new RoomsDao();
        this.hotelDao = new HotelDao();

    }

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

    public ArrayList<Object[]> getForTable(int size, ArrayList<Rooms> rooms){
        ArrayList<Object[]> roomRow = new ArrayList<>();
        for (Rooms room : rooms){
            Object[] row = new Object[size];
            int i = 0;
            row[i++] = room.getId();
            row[i++] = hotelDao.findByID(room.getRoomOtelId()).getHotelName();
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
}
