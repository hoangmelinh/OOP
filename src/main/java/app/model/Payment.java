/*package app.model;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.Objects;

public class Payment {
    private String id;
    private String ticketId;
    private String userId;
    private BigDecimal amount;
    private PaymentMethod method;
    private PaymentStatus status;
    private Instant createdAt;

    public enum PaymentStatus {
        PENDING,
        COMPLETED,
        FAILED,
        REFUNDED
    }

    public enum PaymentMethod {
        CASH,
        CREDIT_CARD,
        DEBIT_CARD,
        E_WALLET
    }

    public Payment() {
    }

    public Payment(String id, String ticketId, String userId, BigDecimal amount, PaymentMethod method, PaymentStatus status, Instant createdAt) {
        this.id = id;
        this.ticketId = ticketId;
        this.userId = userId;
        this.amount = amount;
        this.method = method;
        this.status = status;
        this.createdAt = createdAt;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public PaymentMethod getMethod() {
        return method;
    }

    public void setMethod(PaymentMethod method) {
        this.method = method;
    }

    public PaymentStatus getStatus() {
        return status;
    }

    public void setStatus(PaymentStatus status) {
        this.status = status;
    }

    public Instant getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Instant createdAt) {
        this.createdAt = createdAt;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Payment payment = (Payment) o;
        return Objects.equals(id, payment.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}*/

package app.model;

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

