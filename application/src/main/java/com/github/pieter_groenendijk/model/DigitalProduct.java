package com.github.pieter_groenendijk.model;

import jakarta.persistence.*;

import java.util.Date;

@Entity
@Table(name = "DigitalProduct")
public class DigitalProduct extends Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long digitalProductId;

    @Column(name = "title", nullable = false, length = 100)
    private String accessStatus;

    @Column(name = "releaseDate", nullable = true)
    private Date releaseDate;

    @Column(name = "classification", nullable = true)
    private int classification;

    @Column(name = "mediaType", nullable = false)
    private String mediaType;

// Getters and Setters
    public void setDigitalProductId(Long digitalProductId) {
        this.digitalProductId = digitalProductId;
    }

    public Long getDigitalProductId() {
        return digitalProductId;
    }

    public Date getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(Date releaseDate) {
        this.releaseDate = releaseDate;
    }

    public int getClassification() {
        return classification;
    }

    public void setClassification(int classification) {
        this.classification = classification;
    }

    public String getMediaType() {
        return mediaType;
    }

    public void setMediaType(String mediaType) {
        this.mediaType = mediaType;
    }

    public String getAccessStatus() {
        return accessStatus;
    }

    public void setAccessStatus(String accessStatus) {
        this.accessStatus = accessStatus;
    }
}
