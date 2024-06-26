package entities;

public class Rooms {
    private int id;
    private int roomOtelId;
    private int roomSeasonId;
    private int roomPensionId;
    private int roomPrice;
    private int roomPerson;
    private int roomPersonPrice;
    private int roomChildPrice;
    private int roomM2;
    private int roomStock;
    private String roomName;
    private boolean roomTv;
    private boolean roomMini;
    private boolean roomConsole;
    private boolean roomSafe;
    private boolean roomProject;
    private String roomPensionType;

    public Rooms(int id, int roomOtelId,int roomPersonPrice, int roomChildPrice, String roomName,int roomStock,int roomSeasonId, int roomPensionId, int roomPerson, int roomPrice,int roomM2, boolean roomTv, boolean roomMini, boolean roomConsole, boolean roomSafe, boolean roomProject, String roomPensionType){
        this.id = id;
        this.roomOtelId = roomOtelId;
        this.roomSeasonId = roomSeasonId;
        this.roomPensionId = roomPensionId;
        this.roomPerson = roomPerson;
        this.roomPrice = roomPrice;
        this.roomName = roomName;
        this.roomStock = roomStock;
        this.roomM2 = roomM2;
        this.roomTv = roomTv;
        this.roomMini = roomMini;
        this.roomConsole = roomConsole;
        this.roomSafe = roomSafe;
        this.roomProject = roomProject;
        this.roomPensionType = roomPensionType;
        this.roomPersonPrice = roomPersonPrice;
        this.roomChildPrice = roomChildPrice;
    }

    public Rooms(){

    }

    public int getRoomPersonPrice() {
        return roomPersonPrice;
    }

    public void setRoomPersonPrice(int roomPersonPrice) {
        this.roomPersonPrice = roomPersonPrice;
    }

    public int getRoomChildPrice() {
        return roomChildPrice;
    }

    public void setRoomChildPrice(int roomChildPrice) {
        this.roomChildPrice = roomChildPrice;
    }

    public int getRoomStock() {
        return roomStock;
    }

    public void setRoomStock(int roomStock) {
        this.roomStock = roomStock;
    }

    public String getRoomName() {
        return roomName;
    }

    public void setRoomName(String roomName) {
        this.roomName = roomName;
    }

    public String getRoomPensionType() {
        return roomPensionType;
    }

    public void setRoomPensionType(String roomPensionType) {
        this.roomPensionType = roomPensionType;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getRoomOtelId() {
        return roomOtelId;
    }

    public void setRoomOtelId(int roomOtelId) {
        this.roomOtelId = roomOtelId;
    }

    public int getRoomSeasonId() {
        return roomSeasonId;
    }

    public void setRoomSeasonId(int roomSeasonId) {
        this.roomSeasonId = roomSeasonId;
    }

    public int getRoomPensionId() {
        return roomPensionId;
    }

    public void setRoomPensionId(int roomPensionId) {
        this.roomPensionId = roomPensionId;
    }

    public int getRoomPerson() {
        return roomPerson;
    }

    public void setRoomPerson(int roomPerson) {
        this.roomPerson = roomPerson;
    }

    public int getRoomM2() {
        return roomM2;
    }

    public void setRoomM2(int roomM2) {
        this.roomM2 = roomM2;
    }

    public boolean isRoomTv() {
        return roomTv;
    }

    public void setRoomTv(boolean roomTv) {
        this.roomTv = roomTv;
    }

    public boolean isRoomMini() {
        return roomMini;
    }

    public void setRoomMini(boolean roomMini) {
        this.roomMini = roomMini;
    }

    public boolean isRoomConsole() {
        return roomConsole;
    }

    public void setRoomConsole(boolean roomConsole) {
        this.roomConsole = roomConsole;
    }

    public boolean isRoomSafe() {
        return roomSafe;
    }

    public void setRoomSafe(boolean roomSafe) {
        this.roomSafe = roomSafe;
    }

    public boolean isRoomProject() {
        return roomProject;
    }

    public void setRoomProject(boolean roomProject) {
        this.roomProject = roomProject;
    }

    public int getRoomPrice() {
        return roomPrice;
    }

    public void setRoomPrice(int roomPrice) {
        this.roomPrice = roomPrice;
    }
}


