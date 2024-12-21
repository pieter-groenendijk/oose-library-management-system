package com.github.pieter_groenendijk.model.payment;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "Payment")
public class Payment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "paymentId")
    private Long paymentId;

    @Column(
        name = "molliePaymentId",
        nullable = false,
        length = 255,
        unique = true
    )
    private String molliePaymentId;

    @Column(
        name = "amountInCents",
        nullable = false
    )
    private Long amountInCents;

    @ManyToOne
    @JoinColumn(
        name = "status",
        nullable = false
    )
    private PaymentStatus paymentStatus;

    @Column(
        name = "createdAt",
        nullable = false,
        insertable = false, // It's a generated value, so we want to prevent a manual insertion/update.
        updatable = false // ^^^^
    )
    private LocalDateTime createdAt;

    @Column(
        name = "paidAt",
        nullable = true
    )
    private LocalDateTime paidAt;
}
