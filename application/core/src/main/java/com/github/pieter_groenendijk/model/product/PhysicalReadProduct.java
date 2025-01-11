package com.github.pieter_groenendijk.model.product;

import jakarta.persistence.*;

@Entity
@DiscriminatorValue("PhysicalReadProduct")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
public class PhysicalReadProduct extends PhysicalProductTemplate{

    @Column(name = "ISBN", nullable = true)
    public int ISBN;

    @Column(name = "author", nullable = false, length = 100)
    public String author;

}
