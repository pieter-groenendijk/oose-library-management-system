package com.github.pieter_groenendijk.model;

import jakarta.persistence.*;

import java.util.Date;
import java.util.List;

@Entity
@Table(name = "LoanHistory")
public class LoanHistory {

@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long loanHistoryId;

@JoinColumn(name = "loanId")
    private Long loanId;

@JoinColumn(name = "membershipId")
    private Long membershipId;



// Getters and Setters

    public void setLoanHistoryId(Long loanHistoryId) {
        this.loanHistoryId = loanHistoryId;
    }

    public Long getLoanHistoryId() {
        return loanHistoryId;
    }

    public Long getLoanId() {
        return loanId;
    }

    public void setLoanId(Long loanId) {
        this.loanId = loanId;
    }

    public Long getMembershipId() {
        return membershipId;
    }

    public void setMembershipId(Long membershipId) {
        this.membershipId = membershipId;
    }
}
