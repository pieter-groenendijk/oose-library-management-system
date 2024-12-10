package com.github.pieter_groenendijk.model;

import jakarta.persistence.*;

import java.util.Date;

@Entity
@Table(name = "Loan")
public class Loan {
    @Id
    @GeneratedValue(strategy = jakarta.persistence.GenerationType.IDENTITY)
    private long loanId;
    @Column(name = "startDate", nullable = false)
    @Temporal(TemporalType.DATE)
    private Date startDate;
    @Column(name = "returnBy", nullable = true)
    @Temporal(TemporalType.DATE)
    private Date returnBy;
    @Column(name = "returnedOn", nullable = true)
    @Temporal(TemporalType.DATE)
    private Date returnedOn;
    @Column(name= "loanStatus", nullable = false)
    private String loanStatus;
    @OneToOne
    @JoinColumn(name = "membershipId", nullable = false, unique = true)
    private Membership membership;

    @OneToOne
    @JoinColumn(name = "productId")
    private ProductTemplate productTemplate;

    // Getters and Setters
    public void setLoanId(Long loanId) {
        this.loanId = loanId;
    }

    public Long getLoanId() {
        return loanId;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getReturnBy() {
        return returnBy;
    }

    public void setReturnBy(Date returnBy) {
        this.returnBy = returnBy;
    }

    public Date getReturnedOn() {
        return returnedOn;
    }

    public void setReturnedOn(Date returnedOn) {
        this.returnedOn = returnedOn;
    }

    public String getLoanStatus() {
        return loanStatus;
    }

    public void setLoanStatus(String loanStatus) {
        this.loanStatus = loanStatus;
    }

    public Membership getMembership() {
        return membership;
    }

    public void setMembership(Membership membership) {
        this.membership = membership;
    }

}
