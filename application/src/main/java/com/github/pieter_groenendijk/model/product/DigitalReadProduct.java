package com.github.pieter_groenendijk.model.product;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;

@Entity
public class DigitalReadProduct extends DigitalProductTemplate{

    @Column(name = "type", nullable = false)
    public int type; //TODO: Magazine, ebook, newspaper

    @Column(name = "language", nullable = true)
    public String language;
}
