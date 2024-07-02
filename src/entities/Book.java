package entities;

import java.time.LocalDate;

public class Book {
    private int id;
    private int room_id;
    private int customer_id;
    private String name;
    private String mpno;
    private LocalDate check_in;
    private LocalDate check_out;
    private int person;
    private int child;
    private int price;
    private String status;
    private int hotel_id;

    public Book(int id, int room_id, int customer_id, LocalDate check_in, LocalDate check_out, int person, int child, String name, String mpno, int price, String status) {
        this.id = id;
        this.room_id = room_id;
        this.customer_id = customer_id;
        this.check_in = check_in;
        this.check_out = check_out;
        this.person = person;
        this.child = child;
        this.name = name;
        this.mpno = mpno;
        this.price = price;
        this.status = status;
    }

    public Book() {

    }

    public int getHotel_id() {
        return hotel_id;
    }

    public void setHotel_id(int hotel_id) {
        this.hotel_id = hotel_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMpno() {
        return mpno;
    }

    public void setMpno(String mpno) {
        this.mpno = mpno;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getRoom_id() {
        return room_id;
    }

    public void setRoom_id(int room_id) {
        this.room_id = room_id;
    }

    public int getCustomer_id() {
        return customer_id;
    }

    public void setCustomer_id(int customer_id) {
        this.customer_id = customer_id;
    }

    public LocalDate getCheck_in() {
        return check_in;
    }

    public void setCheck_in(LocalDate check_in) {
        this.check_in = check_in;
    }

    public LocalDate getCheck_out() {
        return check_out;
    }

    public void setCheck_out(LocalDate check_out) {
        this.check_out = check_out;
    }

    public int getPerson() {
        return person;
    }

    public void setPerson(int person) {
        this.person = person;
    }

    public int getChild() {
        return child;
    }

    public void setChild(int child) {
        this.child = child;
    }


    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
