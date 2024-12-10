package com.github.pieter_groenendijk.model.product;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;

@Entity
public class DigitalVideoProduct extends DigitalProductTemplate{

    @Column(name = "duration", nullable = true)
    public int duration;

    @Column(name = "director", nullable = true, length = 100)
    public String director;

    @Column(name = "language", nullable = true, length = 100)
    public String language;

    @Column(name = "videoType", nullable = true, length = 100)
    public String videoType; //TODO: Enum: Movie, series, documentary, other
}
