package business;

import dao.BookDao;
import entities.Book;

import java.util.ArrayList;

public class BookManager {
    private final BookDao bookDao;
    private final RoomsManager roomsManager;
    private final HotelManager hotelManager;


    // Constructor
    public BookManager(){
        this.bookDao = new BookDao();
        this.roomsManager = new RoomsManager();
        this.hotelManager = new HotelManager();

    }

    // Methods

    public ArrayList<Book> findAll(){
        return bookDao.findAll();
    }

    public Book findByID(int id){
        return bookDao.findByID(id);
    }

    public boolean save(Book book){
        if (book.getId() == 0) {
            bookDao.save(book);
        }else {
            bookDao.update(book);
        }
        return true;
    }

    public boolean update(Book book){
        if (book.getId() != 0) {
            bookDao.update(book);
        }
        return true;
    }

    public boolean delete(int id){
        if (id != 0) {
            bookDao.delete(id);
        }
        return true;
    }


    // Table for Booking
    public ArrayList<Object[]> getForTable(int size, ArrayList<Book> books){
        ArrayList<Object[]> bookRow = new ArrayList<>();
        for (Book book : books){
            Object[] row = new Object[size];
            int i = 0;
            row[i++] = book.getId();
            row[i++] = roomsManager.findByID(book.getRoom_id()).getRoomName();
            row[i++] = hotelManager.findByID(roomsManager.findByID(book.getRoom_id()).getRoomOtelId()).getHotelName();
            row[i++] = book.getCustomer_id();
            row[i++] = book.getName();
            row[i++] = book.getMpno();
            row[i++] = book.getCheck_in();
            row[i++] = book.getCheck_out();
            row[i++] = book.getPerson();
            row[i++] = book.getChild();
            row[i++] = book.getPrice();
            row[i++] = book.getStatus();
            bookRow.add(row);
        }
        return bookRow;
    }
}
