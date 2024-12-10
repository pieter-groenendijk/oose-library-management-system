package com.github.pieter_groenendijk.model;

import jakarta.persistence.*;

import java.time.LocalDate;


@Entity
@Table(name = "Lending")
public class Lending {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "lendingId")
    public Long id;

    @Temporal(TemporalType.DATE)
    @Column(
        name = "mustReturnBy",
        nullable = false
    )
    public LocalDate mustReturnBy;
}
