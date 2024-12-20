package com.github.pieter_groenendijk.model.DTO;

public class MembershipRequestDTO {
    private Long membershipTypeId;
    private Long accountId;

    // Getters and Setters
    public Long getMembershipTypeId() {
        return membershipTypeId;
    }

    public void setMembershipTypeId(Long membershipTypeId) {
        this.membershipTypeId = membershipTypeId;
    }

    public Long getAccountId() {
        return accountId;
    }

    public void setAccountId(Long accountId) {
        this.accountId = accountId;
    }
}