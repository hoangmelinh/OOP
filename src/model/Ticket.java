package model;

public class Ticket {
    private String ticketId;
    private String showTimeId;
    private String userId;
    private String seatId;
    private String status; // BOOKED, CANCELLED, PAID
    private String movieId;

    public Ticket() {}

    public Ticket(String ticketId, String showTimeId, String userId, String seatId, String status, String movieId) {
        this.ticketId = ticketId;
        this.showTimeId = showTimeId;
        this.userId = userId;
        this.seatId = seatId;
        this.status = status;
        this.movieId = movieId;
    }

    public String getTicketId() {
        return ticketId;
    }

    public void setTicketId(String ticketId) {
        this.ticketId = ticketId;
    }

    public String getShowTimeId() {
        return showTimeId;
    }

    public void setShowTimeId(String showTimeId) {
        this.showTimeId = showTimeId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getSeatId() {
        return seatId;
    }

    public void setSeatId(String seatId) {
        this.seatId = seatId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getMovieId() {
        return movieId;
    }

    public void setMovieId(String movieId) {
        this.movieId = movieId;
    }

    public void datVe() {
        // stub
    }

    public void huyVe() {
        // stub
    }
}

