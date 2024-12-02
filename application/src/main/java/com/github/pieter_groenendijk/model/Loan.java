package com.github.pieter_groenendijk.model;

import jakarta.persistence.*;

import java.util.Date;

@Entity
@Table(name = "loan")
public class Loan {
    @Id
    @GeneratedValue(strategy = jakarta.persistence.GenerationType.IDENTITY)
    private long loanId;
    @Column(name = "startDate", nullable = false)
    @Temporal(TemporalType.DATE)
    private Date startDate;
    @Column(name = "endDate", nullable = true)
    @Temporal(TemporalType.DATE)
    private Date endDate;
    @Column(name = "returnDate", nullable = true)
    @Temporal(TemporalType.DATE)
    private Date returnDate;
    @Column(name= "loan_status", nullable = false)
    private String loanStatus;
    @OneToOne
    @JoinColumn(name = "membershipId", nullable = false, unique = true)
    private Membership membership;

    // TODO: Check if this is correct. Just a reference to ProductId not Product entity
    @Column(name = "productId", nullable = false)
    private long productId;

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

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public Date getReturnDate() {
        return returnDate;
    }

    public void setReturnDate(Date returnDate) {
        this.returnDate = returnDate;
    }

    public String getLoanStatus() {
        return loanStatus;
    }

    public void setLoanStatus(String loanStatus) {
        this.loanStatus = loanStatus;
    }


    public long getProductId() {
        return productId;
    }

    public void setProductId(long productId) {
        this.productId = productId;
    }

    public Membership getMembership() {
        return membership;
    }

    public void setMembership(Membership membership) {
        this.membership = membership;
    }
}
