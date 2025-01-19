package com.github.pieter_groenendijk.model;

import jakarta.persistence.*;
import com.github.pieter_groenendijk.model.LoansPerGenrePerMembershipId;

@Entity
@Table(name = "vw_Loans_Per_Genre_Per_Membership")
public class LoansPerGenrePerMembership {

    @EmbeddedId
    private LoansPerGenrePerMembershipId id;

    @Column(name = "accountId")
    private long accountId;

    @Column(name = "description")
    private String description;

    @Column(name = "loanCount")
    private int loanCount;

    // Getters and setters

    public long getAccountId() {
        return accountId;
    }

    public void setAccountId(long accountId) {
        this.accountId = accountId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getLoanCount() {
        return loanCount;
    }

    public void setLoanCount(int loanCount) {
        this.loanCount = loanCount;
    }
}