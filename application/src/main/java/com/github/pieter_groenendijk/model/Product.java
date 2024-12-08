package com.github.pieter_groenendijk.model;

import jakarta.persistence.*;

@Entity
@Table (name = "Product")
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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


    // Getters and Setters
    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public String getName() {
        return name;
    }


    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public int getYear() {
        return yearOfRelease;
    }

    public void setYear(int yeaOfReleaser) {
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
}
