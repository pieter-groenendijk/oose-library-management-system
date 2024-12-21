package com.github.pieter_groenendijk.model.fine;

import jakarta.persistence.*;

/**
 * This is not an enum since it's very important that it's always consistent. We don't want stored fines for which
 * the fine type is gone.
 * By using a table, updates to this entity will be done on database level, (mostly) ensuring one source of truth.
 */
@Entity
@Table(name = "FineType")
public class FineType {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "fineTypeId")
    private Long fineTypeId;

    @Column(
        name = "title",
        length = 50,
        unique = true,
        nullable = false
    )
    private String title;

    @Column(
        name = "amountInCents",
        nullable = false
    )
    private Long amountInCents;

    public FineType() {}

    public void setFineTypeId(Long fineTypeId) {
        this.fineTypeId = fineTypeId;
    }

    public Long getFineTypeId() {
        return fineTypeId;
    }
}
