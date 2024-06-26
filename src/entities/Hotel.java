package entities;

public class Hotel {
    private int id;
    private String hotelName;
    private String hotelAdress;
    private String hotelPhone;
    private String hotelEmail;
    private String hotelCity;
    private String hotelDistrict;
    private String pension;
    private String pensionHotelId;
    private int hotelStars;
    private boolean hotelPark;
    private boolean hotelPool;
    private boolean hotelGym;
    private boolean hotelConcierge;
    private boolean hotelSpa;
    private boolean hotelRoomSevice;
    private boolean hotelWifi;

    public String getHotelCity() {
        return hotelCity;
    }

    public void setHotelCity(String hotelCity) {
        this.hotelCity = hotelCity;
    }

    public String getHotelDistrict() {
        return hotelDistrict;
    }

    public void setHotelDistrict(String hotelDistrict) {
        this.hotelDistrict = hotelDistrict;
    }

    public Hotel(int id ,String pension,String pensionHotelId ,String hotelName, String hotelAdress, String hotelPhone, String hotelEmail, String hotelCity, String hotelDistrict, int hotelStars, boolean hotelPark, boolean hotelPool, boolean hotelWifi, boolean hotelGym, boolean hotelConcierge, boolean hotelSpa, boolean hotelRoomSevice){
        this.id = id;
        this.pension = pension;
        this.hotelName = hotelName;
        this.hotelAdress = hotelAdress;
        this.hotelPhone = hotelPhone;
        this.hotelEmail = hotelEmail;
        this.hotelCity = hotelCity;
        this.hotelDistrict = hotelDistrict;
        this.pensionHotelId = pensionHotelId;
        this.hotelStars = hotelStars;
        this.hotelPark = hotelPark;
        this.hotelWifi = hotelWifi;
        this.hotelPool = hotelPool;
        this.hotelGym = hotelGym;
        this.hotelConcierge = hotelConcierge;
        this.hotelSpa = hotelSpa;
        this.hotelRoomSevice = hotelRoomSevice;
    }

    public Hotel(){

    }

    public String getPensionHotelId() {
        return pensionHotelId;
    }

    public void setPensionHotelId(String pensionHotelId) {
        this.pensionHotelId = pensionHotelId;
    }

    public String getPension() {
        return pension;
    }

    public void setPension(String pension) {
        this.pension = pension;
    }

    public boolean isHotelWifi() {
        return hotelWifi;
    }

    public void setHotelWifi(boolean hotelWifi) {
        this.hotelWifi = hotelWifi;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getHotelName() {
        return hotelName;
    }

    public void setHotelName(String hotelName) {
        this.hotelName = hotelName;
    }

    public String getHotelAdress() {
        return hotelAdress;
    }

    public void setHotelAdress(String hotelAdress) {
        this.hotelAdress = hotelAdress;
    }

    public String getHotelPhone() {
        return hotelPhone;
    }

    public void setHotelPhone(String hotelPhone) {
        this.hotelPhone = hotelPhone;
    }

    public String getHotelEmail() {
        return hotelEmail;
    }

    public void setHotelEmail(String hotelEmail) {
        this.hotelEmail = hotelEmail;
    }

    public int getHotelStars() {
        return hotelStars;
    }

    public void setHotelStars(int hotelStars) {
        this.hotelStars = hotelStars;
    }

    public boolean isHotelPark() {
        return hotelPark;
    }

    public void setHotelPark(boolean hotelPark) {
        this.hotelPark = hotelPark;
    }

    public boolean isHotelPool() {
        return hotelPool;
    }

    public void setHotelPool(boolean hotelPool) {
        this.hotelPool = hotelPool;
    }

    public boolean isHotelGym() {
        return hotelGym;
    }

    public void setHotelGym(boolean hotelGym) {
        this.hotelGym = hotelGym;
    }

    public boolean isHotelConcierge() {
        return hotelConcierge;
    }

    public void setHotelConcierge(boolean hotelConcierge) {
        this.hotelConcierge = hotelConcierge;
    }

    public boolean isHotelSpa() {
        return hotelSpa;
    }

    public void setHotelSpa(boolean hotelSpa) {
        this.hotelSpa = hotelSpa;
    }

    public boolean isHotelRoomSevice() {
        return hotelRoomSevice;
    }

    public void setHotelRoomSevice(boolean hotelRoomSevice) {
        this.hotelRoomSevice = hotelRoomSevice;
    }

}
