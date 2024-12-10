package com.github.pieter_groenendijk.model;

import jakarta.persistence.*;

@Entity
public abstract class DigitalProductTemplate extends ProductTemplate {

    @Column(name = "mediaType", nullable = false)
    private String mediaType;
    //TODO: MediaType Enum: Ebook, Audiobook, Movie, Music, Series, Other

// Getters and Setters

    public String getMediaType() {
        return mediaType;
    }

    public void setMediaType(String mediaType) {
        this.mediaType = mediaType;
    }


}
