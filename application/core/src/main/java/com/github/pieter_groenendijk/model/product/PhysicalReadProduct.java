package com.github.pieter_groenendijk.model.product;

import jakarta.persistence.*;

import java.util.List;

@Entity
@DiscriminatorValue("PhysicalReadProduct")
public class PhysicalReadProduct extends PhysicalProductTemplate{

    @Id
    private long productId;

    @Column(name = "ISBN", nullable = true)
    public int ISBN;

    @Column(name = "author", nullable = false, length = 100)
    public String author;

    @OneToMany(mappedBy = "physicalReadProduct", cascade = CascadeType.ALL)
    private List<ProductCopy> copies;

}
