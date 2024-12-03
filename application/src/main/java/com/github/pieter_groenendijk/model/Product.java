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

    @Column(name = "author", nullable = false, length = 100)
    private String author;

    @Column (name = "genre", nullable = false, length = 50)
    private String genre;

    @Column (name = "publicationYear", nullable = false, length = 4)
    private int publicationYear;

    @Column (name = "ISBN", nullable = false, unique = true, length = 13)
    private char ISBN;

    @Column (name = "description", nullable = true, length = 250)
    private String description;

    @Column (name = "totalCopies", nullable = false)
    private int totalCopies;

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

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getAuthor(String author) {
        return author;
    }
    public void setAuthor(String author) {
        this.author = author;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public int getPublicationYear() {
        return publicationYear;
    }

    public void setPublicationYear(int publicationYear) {
        this.publicationYear = publicationYear;
    }

    public char getISBN() {
        return ISBN;
    }

    public void setISBN(char ISBN) {
        this.ISBN = ISBN;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getTotalCopies() {
        return totalCopies;
    }

    public void setTotalCopies(int totalCopies) {
        this.totalCopies = totalCopies;
    }
}
