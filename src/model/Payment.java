package model;

public class Payment {
    private String paymentId;
    private int ticketId;
    private double amount;
    private int userId;
    private String status;

    public Payment(String paymentId, int ticketId, double amount, int userId, String status) {
        this.paymentId = paymentId;
        this.ticketId = ticketId;
        this.amount = amount;
        this.userId = userId;
        this.status = status;
    }

    public String getPaymentId() { return paymentId; }
    public int getTicketId() { return ticketId; }
    public double getAmount() { return amount; }
    public int getUserId() { return userId; }
    public String getStatus() { return status; }

    public void setStatus(String status) { this.status = status; }
}
