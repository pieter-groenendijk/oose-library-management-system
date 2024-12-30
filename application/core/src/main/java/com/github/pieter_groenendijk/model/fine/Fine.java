package com.github.pieter_groenendijk.model.fine;

import com.github.pieter_groenendijk.model.Account;
import com.github.pieter_groenendijk.model.payment.Payment;
import jakarta.persistence.*;
import jakarta.validation.constraints.Min;

import java.time.LocalDateTime;

@Entity
@Table(name = "Fine")
public class Fine {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "fineId")
    private Long fineId;

    @ManyToOne
    @JoinColumn(
        name = "fineType",
        nullable = false
    )
    private FineType fineType;

    @ManyToOne
    @JoinColumn(
        name = "account",
        nullable = false
    )
    private Account account;

    @Column(
        name = "amountInCents",
        nullable = false
    )
    @Min(0)
    private Long amountInCents;

    @Column(
        name = "declaredOn",
        nullable = false,
        insertable = false, // It's a generated value, so we want to prevent a manual insertion/update.
        updatable = false // ^^^
    )
    private LocalDateTime declaredOn;

    @ManyToOne
    @JoinColumn(
        name = "paidBy",
        nullable = true
    )
    private Payment paidBy;
}
