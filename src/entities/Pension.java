package entities;

public class Pension {
    private int id;
    private int hotelId;
    private Pensions type;
    private Hotel hotel;

    public enum Pensions {
        ALLIN,
        ULTRA,
        BED,
        BREAKFAST,
        WITHOUTALCHOL,
        HALF,
        FULL
    }

    public Pension(int id, Pensions type){
        this.id = id;
        this.type = type;

    }

    public Pension(){

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getHotelId() {
        return hotelId;
    }

    public void setHotelId(int hotelId) {
        this.hotelId = hotelId;
    }

    public Pensions getType() {
        return type;
    }

    public void setType(Pensions type) {
        this.type = type;
    }

    public Hotel getHotel() {
        return hotel;
    }

    public void setHotel(Hotel hotel) {
        this.hotel = hotel;
    }
}
