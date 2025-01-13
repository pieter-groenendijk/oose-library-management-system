package com.github.pieter_groenendijk.model.product;

import com.github.pieter_groenendijk.model.MediaType;
import com.github.pieter_groenendijk.model.product.Genre;
import jakarta.persistence.*;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class ProductTemplate {

    @Id
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

    @Column (name = "type", nullable = false, length = 10)
    private String type;
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
