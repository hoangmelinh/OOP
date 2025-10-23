package model;

public class Payment {
    private String paymentId;
    private String ticketId;
    private int amount;
    private String status; // PENDING, SUCCESS, FAILED

    public Payment() {}

    public Payment(String paymentId, String ticketId, int amount, String status) {
        this.paymentId = paymentId;
        this.ticketId = ticketId;
        this.amount = amount;
        this.status = status;
    }

    public String getPaymentId() {
        return paymentId;
    }

    public void setPaymentId(String paymentId) {
        this.paymentId = paymentId;
    }

    public String getTicketId() {
        return ticketId;
    }

    public void setTicketId(String ticketId) {
        this.ticketId = ticketId;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void thanhToan() {
        // stub
    }
}

