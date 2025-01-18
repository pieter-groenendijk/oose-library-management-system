package com.github.pieter_groenendijk.model.payment;

import jakarta.persistence.*;

/**
 * This is not an enum since it's very important that it's always consistent. We don't want stored fines for which
 * the fine type is gone.
 * By using a table, updates to this entity will be done on database level, (mostly) ensuring one source of truth.
 */
@Entity
@Table(name = "PaymentStatus")
public class PaymentStatus {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "paymentStatusId")
    private short paymentStatusId;

    @Column(
        name = "title",
        length = 50,
        nullable = false,
        unique = true
    )
    private String title;
}
