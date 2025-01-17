package com.github.pieter_groenendijk.model.product;

import jakarta.persistence.*;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class ProductTemplate {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "productId")
    private Long productId;

    @Column(name = "name", nullable = false, length = 100)
    private String name;

    @ManyToOne
    @JoinColumn (name = "genreId", nullable = false)
    private Genre genre;

    @Column (name = "yearOfRelease", nullable = false, length = 4)
    private int yearOfRelease;

    @Column (name = "description", nullable = true, length = 250)
    private String description;

    @Column(name = "ageClassification", nullable = true)
    private int ageClassification;

    @Enumerated(EnumType.STRING)
    @Column(name = "mediaType", nullable = false)
    private MediaType mediaType;


    public String getName() {
        return name;
    }

    public Genre getGenre() {
        return genre;
    }

    public void setGenre(Genre genre) {
        this.genre = genre;
    }

    public int getYearOfRelease() {
        return yearOfRelease;
    }

    public void setYearOfRelease(int yearOfRelease) {
        this.yearOfRelease = yearOfRelease;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getAgeClassification() {
        return ageClassification;
    }

    public void setAgeClassification(int ageClassification) {
        this.ageClassification = ageClassification;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public Long getProductId() {
        return productId;
    }

    public MediaType getMediaType() {
        return mediaType;
    }

    public void setMediaType(MediaType mediaType) {
        this.mediaType = mediaType;
    }
}
