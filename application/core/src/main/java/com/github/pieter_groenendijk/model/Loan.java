package com.github.pieter_groenendijk.model;

import com.github.pieter_groenendijk.model.product.ProductCopy;
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
    @Column(name = "returnBy")
    @Temporal(TemporalType.DATE)
    private Date returnBy;

    @Column(name = "extendedReturnBy")
    @Temporal(TemporalType.DATE)
    private Date extendedReturnBy;
    @Column(name = "returnedOn")
    @Temporal(TemporalType.DATE)
    private Date returnedOn;

    @Enumerated(EnumType.STRING)
    @Column(name= "loanStatus")
    private LoanStatus loanStatus;
    @ManyToOne
    @JoinColumn(name = "membershipId", nullable = false, unique = true)
    private Membership membership;

    @OneToOne
    @JoinColumn(name = "productCopyId")
    private ProductCopy productCopy;


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


    public Date getReturnedOn() {
        return returnedOn;
    }

    public void setReturnedOn(Date returnedOn) {
        this.returnedOn = returnedOn;
    }

    public LoanStatus getLoanStatus() {
        return loanStatus;
    }

    public void setLoanStatus(LoanStatus loanStatus) {
        this.loanStatus = loanStatus;
    }

    public Membership getMembership() {
        return membership;
    }

    public void setMembership(Membership membership) {
        this.membership = membership;
    }

    public Date getReturnBy() {
        return returnBy;
    }

    public void setReturnBy(Date returnBy) {
        this.returnBy = returnBy;
    }

    public Date getExtendedReturnBy() {
        return extendedReturnBy;
    }

    public void setExtendedReturnBy(Date extendedReturnBy) {
        this.extendedReturnBy = extendedReturnBy;
    }

}
