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
    @Temporal(TemporalType.DATE)
    private Date startDate;
    @Column(name = "returnBy")
    @Temporal(TemporalType.DATE)
    private LocalDate returnBy; //

    @Column(name = "extendedReturnBy")
    @Temporal(TemporalType.DATE)
    private Date extendedReturnBy;
    @Column(name = "returnedOn")
    @Temporal(TemporalType.DATE)
    private Date returnedOn;
    @Column(name= "loanStatus")
    private String loanStatus;
    @ManyToOne
    @JoinColumn(name = "membershipId", nullable = false, unique = true)
    private Membership membership;

    @OneToOne
    @JoinColumn(name = "productCopyId")
    private ProductCopy productCopy;

    @Column(name = "isExtended")
    private boolean isExtended;

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

    public LocalDate getReturnBy() {
        return returnBy;
    }

    public void setReturnBy(LocalDate returnBy) {
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

    public void setExtended(boolean isExtended) {
        this.isExtended = isExtended;
    }

    public boolean isExtended() { return isExtended; }

}
