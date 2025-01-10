package com.github.pieter_groenendijk.model.product;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class PhysicalReadProduct extends PhysicalProductTemplate{

    @Column(name = "ISBN", nullable = true)
    public int ISBN;

    @Column(name = "author", nullable = false, length = 100)
    public String author;

}
