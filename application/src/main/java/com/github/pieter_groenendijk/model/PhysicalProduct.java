package com.github.pieter_groenendijk.model;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "PhysicalProduct")
public abstract class PhysicalProduct extends Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long physicalProductId;

    @Column(name = "location", nullable = false, length = 100)
    private String location;

    @Column (name = "author", nullable = false, length = 100)
    private String author;
    @OneToMany(mappedBy = "physicalProduct")
    @Column(name = "copies", nullable = false)
    private List<ProductCopy> copies;

// Getters and Setters
    public Long getPhysicalProductId() {
        return physicalProductId;
    }

    public void setPhysicalProductId(Long physicalProductId) {
        this.physicalProductId = physicalProductId;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public List<ProductCopy> getCopies() {
        return copies;
    }

    public void setCopies(List<ProductCopy> copies) {
        this.copies = copies;
    }
}
