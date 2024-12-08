package com.github.pieter_groenendijk.model;

import jakarta.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "Membership")
public class Membership {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long membershipId;

    @ManyToOne
    @JoinColumn(name = "accountId", nullable = false)
    private Account account;  // Reference to Account entity

    @ManyToOne
    @JoinColumn(name = "membershipTypeId", nullable = false)
    private MembershipType membershipType;  // Reference to MembershipType entity

    @Column(name = "isActive", nullable = false)
    private boolean isActive;

    @Column(name = "startDate", nullable = false)
    @Temporal(TemporalType.DATE)
    private Date startDate;

    @Column(name = "endDate")
    @Temporal(TemporalType.DATE)
    private Date endDate;

    @Column(name = "canLoan", nullable = false)
    private boolean canLoan; //CanLoan is different to isActive. isActive is for the membership itself (digital, physical ect), canLoan is thats its not blocked because of fines
}
