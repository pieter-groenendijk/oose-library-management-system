package com.github.pieter_groenendijk.model.product;

import jakarta.persistence.Entity;

@Entity
public class DigitalReadProduct extends DigitalProductTemplate{

    public int type; //TODO: Magazine, ebook, newspaper
    public String language;
}
