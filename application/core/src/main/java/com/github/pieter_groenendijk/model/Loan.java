package com.github.pieter_groenendijk.model;

import com.github.pieter_groenendijk.model.product.ProductCopy;
import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.Date;

@Entity
@Table(name = "Loan")
public class Loan {
    @Id
    @GeneratedValue(strategy = jakarta.persistence.GenerationType.IDENTITY)
    private long loanId;
    @Column(name = "startDate", nullable = false)
    private LocalDate startDate;
    @Column(name = "returnBy")
    private LocalDate returnBy;

    @Column(name = "extendedReturnBy")
    private LocalDate extendedReturnBy;
    @Column(name = "returnedOn")
    private LocalDate returnedOn;

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



    public Long getProductCopy() {
        return productCopy.getProductCopyId();
    }

    public LocalDate getReturnBy() {
        return returnBy;
    }

    public void setReturnBy(LocalDate returnBy) {
        this.returnBy = returnBy;
    }

    public LocalDate getExtendedReturnBy() {
        return extendedReturnBy;
    }

    public void setExtendedReturnBy(LocalDate extendedReturnBy) {
        this.extendedReturnBy = extendedReturnBy;
    }

    public LocalDate getReturnedOn() {
        return returnedOn;
    }

    public void setReturnedOn(LocalDate returnedOn) {
        this.returnedOn = returnedOn;
    }
}
