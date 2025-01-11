package com.github.pieter_groenendijk.model.product;

import com.github.pieter_groenendijk.model.product.PhysicalProductTemplate;
import jakarta.persistence.*;


@Entity
public class ProductCopy {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long productCopyId;

   @ManyToOne
    @JoinColumn(name = "productId", nullable = false)
    private PhysicalProductTemplate physicalProduct;

   @Enumerated(EnumType.STRING)
    @Column (name = "availabilityStatus", nullable = false, length = 50)
    private ProductCopyStatus availabilityStatus;

    public void setCopyId(Long productCopyId) {
        this.productCopyId = productCopyId;
    }

    public Long getProductCopyId() {
        return productCopyId;
    }

    public PhysicalProductTemplate getPhysicalProduct() {
        return physicalProduct;
    }

    public void setPhysicalProduct(PhysicalProductTemplate physicalProduct) {
        this.physicalProduct = physicalProduct;
    }


    public ProductCopyStatus getAvailabilityStatus() {
        return availabilityStatus;
    }

    public void setAvailabilityStatus(ProductCopyStatus availabilityStatus) {
        this.availabilityStatus = availabilityStatus;
    }
}
