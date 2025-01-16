package com.github.pieter_groenendijk.model.product;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;

@Entity
public class DigitalProduct extends DigitalProductTemplate{
    @Column (name = "author", nullable = true, length = 100)
    public String author;

    @Column(name = "edition", nullable = true)
    public int edition;
}
