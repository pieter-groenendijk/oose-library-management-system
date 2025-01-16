package com.github.pieter_groenendijk.model.product;

import com.github.pieter_groenendijk.model.Reservation;
import com.github.pieter_groenendijk.model.product.PhysicalProductTemplate;
import jakarta.persistence.*;


@Entity
public class ProductCopy {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long productCopyId;

    @ManyToOne
    @JoinColumn(name = "productId", nullable = false)
    private PhysicalProduct physicalProduct;

    @Enumerated(EnumType.STRING)
    @Column(name = "availabilityStatus", nullable = false, length = 50)
    private ProductCopyStatus availabilityStatus;


    public Long getProductCopyId() {
        return productCopyId;
    }


    public ProductCopyStatus getAvailabilityStatus() {
        return availabilityStatus;
    }

    public void setAvailabilityStatus(ProductCopyStatus availabilityStatus) {
        this.availabilityStatus = availabilityStatus;
    }

    public void setProductCopyId(long productCopyId) {
        this.productCopyId = productCopyId;
    }

    public PhysicalProduct getPhysicalProductId() {
        return physicalProduct;
    }

    public void setPhysicalProduct(PhysicalProduct physicalProduct) {
        this.physicalProduct = physicalProduct;
    }
}
