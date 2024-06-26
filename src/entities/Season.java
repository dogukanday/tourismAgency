package entities;


import java.time.LocalDate;

public class Season {
    private int id;
    private LocalDate start_date;
    private LocalDate end_date;
    private int otel_id;
    private int discount;

    public Season(int id, LocalDate start_date, LocalDate end_date, int otel_id, int discount) {
        this.id = id;
        this.start_date = start_date;
        this.end_date = end_date;
        this.otel_id = otel_id;
        this.discount = discount;
    }

    public Season(){

    }

    public int getDiscount() {
        return discount;
    }

    public void setDiscount(int discount) {
        this.discount = discount;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public LocalDate getStart_date() {
        return start_date;
    }

    public void setStart_date(LocalDate start_date) {
        this.start_date = start_date;
    }

    public LocalDate getEnd_date() {
        return end_date;
    }

    public void setEnd_date(LocalDate end_date) {
        this.end_date = end_date;
    }

    public int getOtel_id() {
        return otel_id;
    }

    public void setOtel_id(int otel_id) {
        this.otel_id = otel_id;
    }
}
