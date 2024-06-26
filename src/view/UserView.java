package view;

import business.*;
import core.Helper;
import entities.*;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;

public class UserView extends Layout{
    private JPanel container;
    private JLabel lbl_welcome;
    private JTabbedPane tab_hotel;
    private JPanel pnl_hotel;
    private JScrollPane scrl_hotel;
    private JTable tbl_hotel;
    private JPanel pnl_rooms;
    private JPanel pnl_pension;
    private JTable tbl_pension;
    private JScrollPane scrl_pension;
    private JTable tbl_rooms;
    private JScrollPane scrl_rooms;
    private JPanel pnl_season;
    private JTable tbl_season;
    private JScrollPane scrl_season;
    private JPanel pnl_book;
    private JTable tbl_book;
    private JScrollPane scrl_book;
    private Hotel hotel;
    private DefaultTableModel tmdl_hotel;
    private DefaultTableModel tmdl_pension;
    private DefaultTableModel tmdl_rooms;
    private DefaultTableModel tmdl_season;
    private DefaultTableModel tmdl_book;
    private Object[] col_hotel;
    private Object[] col_pension;
    private Object[] col_rooms;
    private Object[] col_season;
    private Object[] col_book;
    private HotelManager hotelManager;
    private PensionManager pensionManager;
    private RoomsManager roomsManager;
    private SeasonManager seasonManager;
    private BookManager bookManager;
    private JPopupMenu book_menu;
    private JPopupMenu hotel_menu;
    private JPopupMenu pension_menu;
    private JPopupMenu rooms_menu;
    private JPopupMenu season_menu;
    private User user;
    private Pension pension;
    private Rooms rooms;
    private Season season;
    private Book book;

    public UserView(User user){
        this.add(container);
        guiInit(1000, 500, "Otel Yönetim Sistemi");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.hotelManager = new HotelManager();
        this.pensionManager = new PensionManager();
        this.roomsManager = new RoomsManager();
        this.seasonManager = new SeasonManager();
        this.bookManager = new BookManager();
        this.rooms = new Rooms();
        this.hotel = new Hotel();
        this.pension = new Pension();
        this.season = new Season();
        this.book = new Book();
        this.tmdl_hotel = new DefaultTableModel();
        this.tmdl_pension = new DefaultTableModel();
        this.tmdl_rooms = new DefaultTableModel();
        this.tmdl_season = new DefaultTableModel();
        this.tmdl_book = new DefaultTableModel();


        this.user = user;
        if (user == null){
            dispose();
        }

        setHotelTable();
        setPensionTable();
        setRoomstable();
        setSeasonTable();
        setBookTable();
        selectPension();
        selectRooms();
        selectHotel();
        selectSeasons();
        selectBooks();
    }

    public void setHotelTable(){
        col_hotel = new Object[]{"ID", "Otel Adı", "Adres", "Telefon", "Email","Şehir", "İlçe", "Yıldız", "Park", "Havuz", "Wifi", "Spor Salonu", "Resepsiyon", "Spa", "Oda Servisi"};
        ArrayList<Object[]> hotels = this.hotelManager.getForTable(col_hotel.length, this.hotelManager.findAll());
        createTable(tmdl_hotel, tbl_hotel, col_hotel, hotels);
        tableRowSelected(tbl_hotel);
    }

    public void setPensionTable(){
        col_pension = new Object[]{"ID", "Otel ID", "Otel Adı", "Pansiyon Türü"};
        ArrayList<Object[]> pensions = this.pensionManager.getForTable(col_pension.length, this.pensionManager.findAll());
        createTable(tmdl_pension, tbl_pension, col_pension, pensions);
        tableRowSelected(tbl_pension);
    }

    public void setRoomstable(){
        col_rooms = new Object[]{"ID", "Otel Adı", "Oda Adı", "Pansiyon Tipi", "Kişi Sayısı", "M2", "TV", "Mini Bar", "Konsol", "Kasa", "Projeksiyon", "Stok", "Yetişkin Fiyatı", "Çocuk Fiyatı", "Fiyat"};
        ArrayList<Object[]> rooms = this.roomsManager.getForTable(col_rooms.length, this.roomsManager.findAll());
        createTable(tmdl_rooms, tbl_rooms, col_rooms, rooms);
        tableRowSelected(tbl_rooms);
    }

    public void setSeasonTable(){
        col_season = new Object[]{"ID", "Başlangıç Tarihi", "Bitiş Tarihi", "Otel ID", "İndirim Oranı"};
        ArrayList<Object[]> seasons = this.seasonManager.getForTable(col_season.length, this.seasonManager.findAll());
        createTable(tmdl_season, tbl_season, col_season, seasons);
        tableRowSelected(tbl_season);
    }

    public void setBookTable(){
        col_book = new Object[]{"ID", "Oda Tipi","Otel Adı", "Müşteri ID", "Ad Soyad", "Telefon", "Giriş Tarihi", "Çıkış Tarihi", "Yetişkin", "Çocuk", "Fiyat", "Durum"};
        ArrayList<Object[]> books = this.bookManager.getForTable(col_book.length, this.bookManager.findAll());
        createTable(tmdl_book, tbl_book, col_book, books);
        tableRowSelected(tbl_book);
    }

    public void selectBooks(){
        tableRowSelected(tbl_book);

        this.book_menu = new JPopupMenu();

        book_menu.add("Rezervasyon Bilgilerini Düzenle").addActionListener(e -> {
            int selectedRow = Helper.getTableSelectedRow(tbl_book, 0);
            if (selectedRow == -1) {
                Helper.showMessage("error");
            } else {
                BookEditView bookEditView = new BookEditView(bookManager.findByID(selectedRow));
                bookEditView.addWindowListener(new WindowAdapter() {
                    @Override
                    public void windowClosed(WindowEvent e) {
                        setBookTable();
                        setRoomstable();
                    }
                });
            }
        });

        book_menu.add("Yeni Rezervasyon Ekle").addActionListener(e -> {
            Book book = new Book();
            BookEditView bookEditView = new BookEditView(book);
            bookEditView.addWindowListener(new WindowAdapter() {
                @Override
                public void windowClosed(WindowEvent e) {
                    setBookTable();
                    setRoomstable();
                }
            });
        });

        book_menu.add("Rezervasyon Sil").addActionListener(e -> {
            int selectedRow = Helper.getTableSelectedRow(tbl_book, 0);
            if (selectedRow == -1) {
                Helper.showMessage("error");
            } else {
                Book booking = bookManager.findByID(selectedRow);
                bookManager.delete(selectedRow);
                setBookTable();
                int deletedRoom = roomsManager.findByID(booking.getRoom_id()).getRoomStock() + 1;
                roomsManager.updateStock(booking.getRoom_id(),deletedRoom );
                setRoomstable();
            }
        });

        this.tbl_book.setComponentPopupMenu(book_menu);
    }


    public void selectSeasons(){
        tableRowSelected(tbl_season);
        this.season_menu = new JPopupMenu();

        season_menu.add("Sezon Bilgilerini Düzenle").addActionListener(e -> {
            int selectedRow = Helper.getTableSelectedRow(tbl_season, 0);
            if (selectedRow == -1) {
                Helper.showMessage("error");
            } else {
                SeasonEditView seasonEditView = new SeasonEditView(seasonManager.findByID(selectedRow));
                seasonEditView.addWindowListener(new WindowAdapter() {
                    @Override
                    public void windowClosed(WindowEvent e) {
                        setSeasonTable();
                    }
                });
            }
        });

        season_menu.add("Yeni Sezon Ekle").addActionListener(e -> {
            Season season = new Season();
            SeasonEditView seasonEditView = new SeasonEditView(season);
            seasonEditView.addWindowListener(new WindowAdapter() {
                @Override
                public void windowClosed(WindowEvent e) {
                    setSeasonTable();
                }
            });
        });

        season_menu.add("Sezon Sil").addActionListener(e -> {
            int selectedRow = Helper.getTableSelectedRow(tbl_season, 0);
            if (selectedRow == -1) {
                Helper.showMessage("error");
            } else {
                seasonManager.delete(selectedRow);
                setSeasonTable();
            }
        });

        tbl_season.setComponentPopupMenu(season_menu);
    }

    public void selectRooms() {
        tableRowSelected(tbl_rooms);

        this.rooms_menu = new JPopupMenu();

        rooms_menu.add("Oda Bilgilerini Düzenle").addActionListener(e -> {
            int selectedRow = Helper.getTableSelectedRow(tbl_rooms, 0);
            if (selectedRow == -1) {
                Helper.showMessage("error");
            } else {
                RoomsEditView roomsEditView = new RoomsEditView(roomsManager.findByID(selectedRow));
                roomsEditView.addWindowListener(new WindowAdapter() {
                    @Override
                    public void windowClosed(WindowEvent e) {
                        setRoomstable();
                    }
                });
            }
        });

        rooms_menu.add("Yeni Oda Ekle").addActionListener(e -> {
            Rooms rooms = new Rooms();
            RoomsEditView roomsEditView = new RoomsEditView(rooms);
            roomsEditView.addWindowListener(new WindowAdapter() {
                @Override
                public void windowClosed(WindowEvent e) {
                    setRoomstable();
                }
            });
        });

        rooms_menu.add("Oda Sil").addActionListener(e -> {
            int selectedRow = Helper.getTableSelectedRow(tbl_rooms, 0);
            if (selectedRow == -1) {
                Helper.showMessage("error");
            } else {
                roomsManager.delete(selectedRow);
                setRoomstable();
            }
        });

        this.tbl_rooms.setComponentPopupMenu(rooms_menu);

    }

    public void selectPension(){
        tableRowSelected(tbl_pension);

        this.pension_menu = new JPopupMenu();

        pension_menu.add("Pansiyon Bilgilerini Düzenle").addActionListener(e -> {
            int selectedRow = Helper.getTableSelectedRow(tbl_pension, 0);
            if (selectedRow == -1) {
                Helper.showMessage("error");
            } else {
                PensionEditView pensionEditView = new PensionEditView(pensionManager.getById(selectedRow));
                pensionEditView.addWindowListener(new WindowAdapter() {
                    @Override
                    public void windowClosed(WindowEvent e) {
                        setPensionTable();
                    }
                });
            }
        });

        pension_menu.add("Yeni Pansiyon Ekle").addActionListener(e -> {
            Pension pension = new Pension();
            PensionEditView pensionEditView = new PensionEditView(pension);
            pensionEditView.addWindowListener(new WindowAdapter() {
                @Override
                public void windowClosed(WindowEvent e) {
                    setPensionTable();
                }
            });
        });

        pension_menu.add("Pansiyon Sil").addActionListener(e -> {
            int selectedRow = Helper.getTableSelectedRow(tbl_pension, 0);
            if (selectedRow == -1) {
                Helper.showMessage("error");
            } else {
                pensionManager.delete(selectedRow);
                setPensionTable();
            }
        });



        this.tbl_pension.setComponentPopupMenu(pension_menu);


        }




    public void selectHotel(){
        tableRowSelected(tbl_hotel);

        this.hotel_menu = new JPopupMenu();

        hotel_menu.add("Otel Bilgilerini Düzenle").addActionListener(e -> {
            int selectedRow = Helper.getTableSelectedRow(tbl_hotel,0);
            if (selectedRow == -1){
                Helper.showMessage("error");
            }else{
                HotelEditView hotelEditView = new HotelEditView(hotelManager.findByID(selectedRow));
                hotelEditView.addWindowListener(new WindowAdapter() {
                    @Override
                    public void windowClosed(WindowEvent e) {
                        setHotelTable();
                    }
                });
            }

        });

        hotel_menu.add("Yeni Otel Ekle").addActionListener(e -> {
            Hotel hotel = new Hotel();
            HotelEditView hotelEditView = new HotelEditView(hotel);
            hotelEditView.addWindowListener(new WindowAdapter() {
                @Override
                public void windowClosed(WindowEvent e) {
                    setHotelTable();
                }
            });
        });

        hotel_menu.add("Otel Sil").addActionListener(e -> {
            int selectedRow = Helper.getTableSelectedRow(tbl_hotel,0);
            if (selectedRow == -1){
                Helper.showMessage("error");
            }else{
                hotelManager.delete(selectedRow);
                setHotelTable();
            }
        });

        this.tbl_hotel.setComponentPopupMenu(hotel_menu);

    }



}
