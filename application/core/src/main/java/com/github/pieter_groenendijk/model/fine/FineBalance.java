package com.github.pieter_groenendijk.model.fine;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import org.hibernate.annotations.Immutable; // TODO: Perhaps bypass specific usage of hibernate, and prefer jpa.

@Entity
@Table(name = "vw_FineBalance")
@Immutable
public class FineBalance {
    @Id
    @Column(
        name = "account",
        nullable = false
    )
    private Long account;

    @Column(
        name = "balanceInCents",
        nullable = false
    )
    private Long balanceInCents;

    public void setAccount(Long account) {
        this.account = account;
    }

    public Long getAccount() {
        return account;
    }

    public Long getBalanceInCents() {
        return balanceInCents;
    }

    public void setBalanceInCents(Long balanceInCents) {
        this.balanceInCents = balanceInCents;
    }
}
