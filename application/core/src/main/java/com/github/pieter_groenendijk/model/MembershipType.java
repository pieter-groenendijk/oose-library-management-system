package com.github.pieter_groenendijk.model;

import jakarta.persistence.*;

@Entity
@Table(name = "MembershipType")
public class MembershipType {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long membershipTypeId;

    @Column(name = "description", length = 150)
    private String description;

    @Column(name = "digitalProducts", nullable = false)
    private boolean digitalProducts;

    @Column(name = "physicalProducts", nullable = false)
    private boolean physicalProducts;

    @Column(name = "maxLendings", nullable = false)
    private int maxLendings;

    // Getters and Setters
    public Long getMembershipTypeId() {
        return membershipTypeId;
    }

    public void setMembershipTypeId(Long membershipTypeId) {
        this.membershipTypeId = membershipTypeId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean isDigitalProducts() {
        return digitalProducts;
    }

    public void setDigitalProducts(boolean digitalProducts) {
        this.digitalProducts = digitalProducts;
    }

    public boolean isPhysicalProducts() {
        return physicalProducts;
    }

    public void setPhysicalProducts(boolean physicalProducts) {
        this.physicalProducts = physicalProducts;
    }

    public int getMaxLendings() {
        return maxLendings;
    }

    public void setMaxLendings(int maxLendings) {
        this.maxLendings = maxLendings;
    }
}
