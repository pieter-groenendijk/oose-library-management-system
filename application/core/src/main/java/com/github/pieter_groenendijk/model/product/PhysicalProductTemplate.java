package com.github.pieter_groenendijk.model.product;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
public abstract class PhysicalProductTemplate extends ProductTemplate {

    @Column(name = "location", nullable = false, length = 100)
    private String location;

    @Column (name = "author", nullable = false, length = 100)
    private String author;
    @OneToMany(mappedBy = "physicalProduct", cascade = CascadeType.ALL)
    private List<ProductCopy> copies;


// Getters and Setters
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
