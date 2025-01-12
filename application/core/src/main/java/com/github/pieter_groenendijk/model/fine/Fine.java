package com.github.pieter_groenendijk.model.fine;

import com.github.pieter_groenendijk.model.Account;
import com.github.pieter_groenendijk.model.payment.Payment;
import jakarta.persistence.*;
import jakarta.validation.constraints.Min;

import java.time.LocalDateTime;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(
    name = "associationType",
    discriminatorType = DiscriminatorType.STRING,
    length = 50
)
@Table(name = "Fine")
public abstract class Fine { // With abstract we enforce that it's an exclusive subtype.
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

    public Long getFineId() {
        return fineId;
    }

    public void setFineId(Long fineId) {
        this.fineId = fineId;
    }

    public FineType getFineType() {
        return fineType;
    }

    public void setFineType(FineType fineType) {
        this.fineType = fineType;
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    public @Min(0) Long getAmountInCents() {
        return amountInCents;
    }

    public void setAmountInCents(@Min(0) Long amountInCents) {
        this.amountInCents = amountInCents;
    }

    public LocalDateTime getDeclaredOn() {
        return declaredOn;
    }

    public void setDeclaredOn(LocalDateTime declaredOn) {
        this.declaredOn = declaredOn;
    }

    public Payment getPaidBy() {
        return paidBy;
    }

    public void setPaidBy(Payment paidBy) {
        this.paidBy = paidBy;
    }
}
