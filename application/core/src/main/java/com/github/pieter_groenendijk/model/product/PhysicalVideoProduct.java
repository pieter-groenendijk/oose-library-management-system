package com.github.pieter_groenendijk.model.product;

import jakarta.persistence.*;

@Entity
@DiscriminatorValue("PhysicalVideoProduct")
public class PhysicalVideoProduct extends PhysicalProductTemplate{
    @Column(name = "duration", nullable = true)
    public int duration;

    @Column(name = "director", nullable = true, length = 100)
    public String director;

    @Column(name = "language", nullable = true, length = 100)
    public String language;


}
