package com.github.pieter_groenendijk.model.DTO;

public class MembershipTypeRequestDTO {

    private String description;
    private boolean digitalProducts;
    private boolean physicalProducts;
    private int maxLendings;

    // Getters and Setters
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