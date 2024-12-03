package com.github.pieter_groenendijk.model;

import jakarta.persistence.*;

@Entity
@Table (name = "Product")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long productId;

    @Column(name = "productName", nullable = false, length = 100)
    private String productName;

    @Column (name = "genre", nullable = false, length = 50)
    private String genre;

    @Column (name = "year", nullable = false, length = 4)
    private int year;

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

    public String getProductName() {
        return productName;
    }


    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
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
