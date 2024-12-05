package com.github.pieter_groenendijk.model;

import jakarta.persistence.*;


@Entity
@Table (name = "Copies")
public class Copies {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long copyId;

   @ManyToOne
    @JoinColumn(name = "productId", nullable = false)
    private PhysicalProduct physicalProduct;

    @Column (name = "availabilityStatus", nullable = false, length = 50)
    private String availabilityStatus;

    @Column (name = "isAvailable", nullable = false)
    private boolean isAvailable;

    public void setCopyId(Long copyId) {
        this.copyId = copyId;
    }

    public Long getCopyId() {
        return copyId;
    }

    public String getAvailabilityStatus() {
        return availabilityStatus;
    }

    public void setAvailabilityStatus(String availabilityStatus) {
        this.availabilityStatus = availabilityStatus;
    }

    public boolean isAvailable() {
        return isAvailable;
    }

    public void setAvailable(boolean available) {
        isAvailable = available;
    }

    public PhysicalProduct getPhysicalProduct() {
        return physicalProduct;
    }

    public void setPhysicalProduct(PhysicalProduct physicalProduct) {
        this.physicalProduct = physicalProduct;
    }
}
