package com.github.pieter_groenendijk.model;

import jakarta.persistence.*;
import com.github.pieter_groenendijk.model.product.Genre;

@Entity
@Table(name = "LendingLimit")
public class LendingLimit {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long lendingLimitId;

    @ManyToOne
    @JoinColumn(name = "membershipTypeId", nullable = false)
    private MembershipType membershipType;

    @ManyToOne
    @JoinColumn(name = "genreId", nullable = false)
    private Genre genre;

    @Column(name = "maxLendings", nullable = false)
    private int maxLendings;

    public long getLendingLimitId() {return lendingLimitId; }
    public void setLendingLimitId(long lendingLimitId) {this.lendingLimitId = lendingLimitId;}
    public MembershipType getMembershipType() {return membershipType;}
    public void setMembershipType(MembershipType membershipType) {this.membershipType = membershipType;}
    public Genre getGenre() {return genre;}
    public void setGenre(Genre genre) {this.genre = genre;}
}