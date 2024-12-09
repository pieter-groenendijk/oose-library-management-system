package com.github.pieter_groenendijk.model;

import jakarta.persistence.*;

import java.util.Date;

@Entity
@Table(name = "DigitalProduct")
public abstract class DigitalProduct extends Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long digitalProductId;

    @Column(name = "mediaType", nullable = false)
    private String mediaType;

// Getters and Setters
    public void setDigitalProductId(Long digitalProductId) {
        this.digitalProductId = digitalProductId;
    }

    public Long getDigitalProductId() {
        return digitalProductId;
    }

    public String getMediaType() {
        return mediaType;
    }

    public void setMediaType(String mediaType) {
        this.mediaType = mediaType;
    }


}
