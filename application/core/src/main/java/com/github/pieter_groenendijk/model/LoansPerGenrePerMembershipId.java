package com.github.pieter_groenendijk.model;

import jakarta.persistence.*;
import java.io.Serializable;

@Embeddable
public class LoansPerGenrePerMembershipId implements Serializable {

    @Column(name = "membershipId")
    private long membershipId;

    @Column(name = "genreId")
    private long genreId;

    private void setMembershipId(long membershipId) {this.membershipId = membershipId;}
    private long getMembershipId() {return this.membershipId;}
    private void setGenreId(long genreId) {this.genreId = genreId;}
    private long getGenreId() {return this.genreId;}
}