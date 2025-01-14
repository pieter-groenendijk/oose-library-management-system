package com.github.pieter_groenendijk.model.DTO;

import com.github.pieter_groenendijk.model.LoanStatus;

import java.time.LocalDate;

public class LoanRequestDTO {
    private LocalDate startDate = LocalDate.now();
    private long productCopyId;
    private LoanStatus loanStatus;
    private long membershipId;

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }


    public long getProductCopyId() {
        return productCopyId;
    }

    public void setProductCopyId(long productCopyId) {
        this.productCopyId = productCopyId;
    }

    public LoanStatus getLoanStatus() {
        return loanStatus != null ? loanStatus : LoanStatus.ACTIVE;
    }

    public void setLoanStatus(LoanStatus loanStatus) {
        this.loanStatus = loanStatus;
    }

    public long getMembershipId() {
        return membershipId;
    }
}
