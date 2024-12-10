package com.github.pieter_groenendijk.model.product;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;

@Entity
public class DigitalAudioProduct extends DigitalProductTemplate {

    @Column(name = "duration", nullable = true)
    private int duration;

    @Column(name = "artist", nullable = true, length = 100)
    private String artist;

}
