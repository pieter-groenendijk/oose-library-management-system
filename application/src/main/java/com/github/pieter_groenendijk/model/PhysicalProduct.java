package com.github.pieter_groenendijk.model;

import jakarta.persistence.*;

@Entity
@Table(name = "PhysicalProduct")
public class PhysicalProduct extends Product {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long physicalProductId;

    @Column(name = "title", nullable = false, length = 100)
    private String location;

    @Column (name = "author", nullable = false, length = 100)
    private String author;


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
}
