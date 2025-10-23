package model;

public class Seat {
    private String seatId;
    private String row;
    private int number;
    private String status; // AVAILABLE, BOOKED, HOLD

    public Seat() {}

    public Seat(String seatId, String row, int number, String status) {
        this.seatId = seatId;
        this.row = row;
        this.number = number;
        this.status = status;
    }

    public String getSeatId() {
        return seatId;
    }

    public void setSeatId(String seatId) {
        this.seatId = seatId;
    }

    public String getRow() {
        return row;
    }

    public void setRow(String row) {
        this.row = row;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void chonGhe() {
        // stub
    }
}
