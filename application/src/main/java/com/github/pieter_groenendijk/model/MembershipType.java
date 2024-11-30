package com.github.pieter_groenendijk.model;

import javax.persistence.*;

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
}
