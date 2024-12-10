package com.github.pieter_groenendijk.model.product;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;

@Entity
public class PhysicalReadProduct extends PhysicalProductTemplate{

    @Column(name = "ISBN", nullable = false)
    public int ISBN;

    @Column(name = "author", nullable = false, length = 100)
    public String author;

}
