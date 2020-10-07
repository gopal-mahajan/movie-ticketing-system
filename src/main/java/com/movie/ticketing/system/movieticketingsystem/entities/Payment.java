package com.movie.ticketing.system.movieticketingsystem.entities;

import com.movie.ticketing.system.movieticketingsystem.DTO.PaymentStatus;

import javax.persistence.*;
import java.util.UUID;

@Entity
@Table(name = "Payment")
public class Payment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String transactionId;
    private Long userId;
    private Integer amount;
    @Enumerated(EnumType.STRING)
    private PaymentStatus Status = PaymentStatus.NOT_PAID;

    public Payment(Long userId, int amount) {
        this.userId = userId;
        this.transactionId = UUID.randomUUID().toString();
        this.amount = amount;
    }

    Payment() {
    }

    public String getTransactionId() {
        return transactionId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public PaymentStatus getStatus() {
        return Status;
    }

    public void setStatus(PaymentStatus status) {
        Status = status;
    }
}
