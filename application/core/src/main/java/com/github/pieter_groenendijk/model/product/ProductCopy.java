package com.github.pieter_groenendijk.model.product;

import com.github.pieter_groenendijk.model.Reservation;
import com.github.pieter_groenendijk.model.product.PhysicalProductTemplate;
import jakarta.persistence.*;


@Entity
@DiscriminatorValue("ProductCopy")
public class ProductCopy {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long productCopyId;

    @ManyToOne
    @JoinColumn(name = "productId", nullable = false)
    private PhysicalReadProduct physicalReadProduct;

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


    public Reservation getReservation() {
        return null;
    }

    public void setProductCopyId(long productCopyId) {
        this.productCopyId = productCopyId;
    }

    public PhysicalReadProduct getPhysicalReadProduct() {
        return physicalReadProduct;
    }

    public void setPhysicalReadProduct(PhysicalReadProduct physicalReadProduct) {
        this.physicalReadProduct = physicalReadProduct;
    }
}
