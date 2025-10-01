package model;

public class Ticket {
    private int ticketId;
    private String seat;
    private int showtimeId;
    private int userId;
    private String status;

    public Ticket(String seat, int showtimeId, int userId, String status) {
        this.seat = seat;
        this.showtimeId = showtimeId;
        this.userId = userId;
        this.status = status;
    }

    public Ticket(int ticketId, String seat, int showtimeId, int userId, String status) {
        this.ticketId = ticketId;
        this.seat = seat;
        this.showtimeId = showtimeId;
        this.userId = userId;
        this.status = status;
    }

    public int getTicketId() { return ticketId; }
    public String getSeat() { return seat; }
    public int getShowtimeId() { return showtimeId; }
    public int getUserId() { return userId; }
    public String getStatus() { return status; }

    public void setSeat(String seat) { this.seat = seat; }
    public void setShowtimeId(int showtimeId) { this.showtimeId = showtimeId; }
    public void setUserId(int userId) { this.userId = userId; }
    public void setStatus(String status) { this.status = status; }
}
