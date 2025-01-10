package com.github.pieter_groenendijk.model.DTO;

import java.util.Date;

public class ExtendLoanDTO {
    private long loanId;
    private Date returnBy;

    public ExtendLoanDTO() {
    }

    public ExtendLoanDTO(long loanId, Date returnBy) {
        this.loanId = loanId;
        this.returnBy = returnBy;
    }

    public long getLoanId() {
        return loanId;
    }

    public void setLoanId(long loanId) {
        this.loanId = loanId;
    }

    public Date getReturnBy() {
        return returnBy;
    }

    public void setReturnBy(Date returnBy) {
        this.returnBy = returnBy;
    }
}
