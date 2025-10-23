package model;
class StandardTicket extends Ticket {
    public StandardTicket(String ticketId, String userId, String showtimeId, String seatId, boolean status) {
        super(ticketId, userId, showtimeId, seatId, status);
    }
    @Override
    public String toString() {
        return "StandardTicket{" +
                "ticketId='" + getTicketId() + '\'' +
                ", userId='" + getUserId() + '\'' +
                ", showtimeId='" + getShowtimeId() + '\'' +
                ", seatId='" + getSeatId() + '\'' +
                ", status=" + isStatus() +
                '}';
    }
}
class VipTicket extends Ticket {
    private String loungeAccessCode;
    private double historyDiscount;
    /**
     * Constructor cho VipTicket
     * @param ticketId
     * @param userId
     * @param showtimeId
     * @param seatId
     * @param status
     * @param loungeAccessCode
     * @param historyDiscount
     */
    public VipTicket(String ticketId, String userId, String showtimeId, String seatId, boolean status,
                     String loungeAccessCode, double historyDiscount) {
        super(ticketId, userId, showtimeId, seatId, status);
        this.loungeAccessCode = loungeAccessCode;
        this.historyDiscount = historyDiscount; // Gán chiết khấu vào đây
    }
    public String getLoungeAccessCode() {
        return loungeAccessCode;
    }
    public void setLoungeAccessCode(String loungeAccessCode) {
        this.loungeAccessCode = loungeAccessCode;
    }
    public double getHistoryDiscount() {
        return historyDiscount;
    }
    public void setHistoryDiscount(double historyDiscount) {
        this.historyDiscount = historyDiscount;
    }
    @Override
    public String toString() {
        return "VipTicket{" +
                "ticketId='" + getTicketId() + '\'' +
                ", userId='" + getUserId() + '\'' +
                ", showtimeId='" + getShowtimeId() + '\'' +
                ", seatId='" + getSeatId() + '\'' +
                ", status=" + isStatus() +
                ", loungeAccessCode='" + loungeAccessCode + '\'' +
                ", historyDiscount=" + historyDiscount +
                '}';
    }
}
public class Ticket {
    private String ticketId;
    private String userId;
    private String showtimeId;
    private String seatId;
    private boolean status;

    public Ticket() {}

    public Ticket(String ticketId, String userId, String showtimeId, String seatId, boolean status) {
        this.ticketId = ticketId;
        this.userId = userId;
        this.showtimeId = showtimeId;
        this.seatId = seatId;
        this.status = status;
    }
    public String getTicketId() {
        return ticketId;
    }

    public void setTicketId(String ticketId) {
        this.ticketId = ticketId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getShowtimeId() {
        return showtimeId;
    }

    public void setShowtimeId(String showtimeId) {
        this.showtimeId = showtimeId;
    }

    public String getSeatId() {
        return seatId;
    }

    public void setSeatId(String seatId) {
        this.seatId = seatId;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Ticket{" +
                "ticketId='" + ticketId + '\'' +
                ", userId='" + userId + '\'' +
                ", showtimeId='" + showtimeId + '\'' +
                ", seatId='" + seatId + '\'' +
                ", status=" + status +
                '}';
    }
}
