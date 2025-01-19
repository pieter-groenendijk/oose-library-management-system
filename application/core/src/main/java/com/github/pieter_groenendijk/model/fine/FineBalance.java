package com.github.pieter_groenendijk.model.fine;

import com.github.pieter_groenendijk.model.Account;
import jakarta.persistence.*;
import org.hibernate.annotations.Immutable; // TODO: Perhaps bypass specific usage of hibernate, and prefer jpa.

@Entity
@Table(name = "vw_FineBalance")
@Immutable
public class FineBalance {
    @Id
    @ManyToOne
    @JoinColumn(
        name = "account",
        nullable = false
    )
    private Account account;

    @Column(
        name = "balanceInCents",
        nullable = false
    )
    private Long balanceInCents;

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    public Long getBalanceInCents() {
        return balanceInCents;
    }

    public void setBalanceInCents(Long balanceInCents) {
        this.balanceInCents = balanceInCents;
    }
}
