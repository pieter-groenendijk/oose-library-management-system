package com.github.pieter_groenendijk.model;

import jakarta.persistence.*;


@Entity
@Table (name = "ProductCopy")
public class ProductCopy {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long productCopyId;

   @ManyToOne
    @JoinColumn(name = "productId", nullable = false)
    private PhysicalProduct physicalProduct;

    @Column (name = "availabilityStatus", nullable = false, length = 50)
    private String availabilityStatus;

    @Column (name = "isDamaged", nullable = false)
    private boolean isDamaged;

    public void setCopyId(Long productCopyId) {
        this.productCopyId = productCopyId;
    }

    public Long getCopyId() {
        return productCopyId;
    }

    public String getAvailabilityStatus() {
        return availabilityStatus;
    }

    public void setAvailabilityStatus(String availabilityStatus) {
        this.availabilityStatus = availabilityStatus;
    }

    public PhysicalProduct getPhysicalProduct() {
        return physicalProduct;
    }

    public void setPhysicalProduct(PhysicalProduct physicalProduct) {
        this.physicalProduct = physicalProduct;
    }

    public boolean isDamaged() {
        return isDamaged;
    }

    public void setDamaged(boolean damaged) {
        isDamaged = damaged;
    }
}
