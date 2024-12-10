package com.github.pieter_groenendijk.model.product;

import jakarta.persistence.*;

@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public abstract class ProductTemplate {

    @Id
    @Column(name = "productId")
    private Long productId;

    @Column(name = "name", nullable = false, length = 100)
    private String name;

    @Column (name = "genre", nullable = false, length = 50)
    private String genre;

    @Column (name = "yearOfRelease", nullable = false, length = 4)
    private int yearOfRelease;

    @Column (name = "description", nullable = true, length = 250)
    private String description;

    @Column (name = "type", nullable = false, length = 10)
    private String type;
    @Column(name = "ageClassification", nullable = true)
    private int ageClassification;

    @Enumerated(EnumType.STRING)
    @Column(name = "mediaType", nullable = false)
    private String mediaType;


    public String getName() {
        return name;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
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

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
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
}
