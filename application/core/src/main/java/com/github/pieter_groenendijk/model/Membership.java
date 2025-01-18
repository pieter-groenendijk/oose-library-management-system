package com.github.pieter_groenendijk.model;

import jakarta.persistence.*;

import java.util.ArrayList;
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
    private Account account;

    @ManyToOne
    @JoinColumn(name = "membershipTypeId", nullable = false)
    private MembershipType membershipType;

    @Column(name = "startDate", nullable = false)
    @Temporal(TemporalType.DATE)
    private Date startDate;

    @Column(name = "endDate")
    @Temporal(TemporalType.DATE)
    private Date endDate;

    @Column(name = "isBlocked", nullable = false)
    private boolean isBlocked;

    @Column(name = "isDeleted", nullable = false)
    private boolean isDeleted;

    // Getters and Setters
    public Long getMembershipId() {
        return membershipId;
    }

    public void setMembershipId(Long membershipId) {
        this.membershipId = membershipId;
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    public MembershipType getMembershipType() {
        return membershipType;
    }

    public void setMembershipType(MembershipType membershipType) {
        this.membershipType = membershipType;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public boolean isBlocked() {
        return isBlocked;
    }

    public void setBlocked(boolean blocked) {
        isBlocked = blocked;
    }

//    public List<Reservation> getReservations() {
//        return reservations;
//    }
//
//    public void setReservations(List<Reservation> reservations) {
//        this.reservations = reservations;}

    public boolean isDeleted() {
        return isDeleted;
    }

    public void setDeleted(boolean deleted) {
        isDeleted = deleted;
    }
}