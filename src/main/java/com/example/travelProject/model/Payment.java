package com.example.travelProject.model;

import com.example.travelProject.enums.PaymentStatus;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Payment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Enumerated(EnumType.ORDINAL)
    private PaymentStatus status;
    private Integer forPayment;
    private Integer confirmationCode;
    @ManyToOne
    @JoinColumn(name = "client_id")
    private User client;

    private LocalDateTime time;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public PaymentStatus getStatus() {
        return status;
    }

    public void setStatus(PaymentStatus status) {
        this.status = status;
    }

    public Integer getForPayment() {
        return forPayment;
    }

    public void setForPayment(Integer forPayment) {
        this.forPayment = forPayment;
    }

    public Integer getConfirmationCode() {
        return confirmationCode;
    }

    public void setConfirmationCode(Integer confirmationCode) {
        this.confirmationCode = confirmationCode;
    }

    public User getClient() {
        return client;
    }

    public void setClient(User client) {
        this.client = client;
    }

    public LocalDateTime getTime() {
        return time;
    }

    public void setTime(LocalDateTime time) {
        this.time = time;
    }
}
