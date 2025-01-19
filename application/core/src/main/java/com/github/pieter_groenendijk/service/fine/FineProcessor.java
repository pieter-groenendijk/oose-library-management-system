package com.github.pieter_groenendijk.service.fine;

import com.github.pieter_groenendijk.model.Account;
import com.github.pieter_groenendijk.model.fine.Fine;
import com.github.pieter_groenendijk.repository.IAccountRepository;
import com.github.pieter_groenendijk.repository.fine.IFineRepository;

public class FineProcessor {
    private final IAccountRepository ACCOUNT_REPOSITORY;
    private final IFineRepository FINE_REPOSITORY;

    private final long BLOCK_THRESHOLD_IN_CENTS;

    public FineProcessor(
        IAccountRepository accountRepository,
        IFineRepository fineRepository,
        long blockThresholdInCents
    ) {
        this.ACCOUNT_REPOSITORY = accountRepository;
        this.FINE_REPOSITORY = fineRepository;
        this.BLOCK_THRESHOLD_IN_CENTS = blockThresholdInCents;
    }

    public void addFine(Fine fine) throws Exception {
        this.FINE_REPOSITORY.store(fine);

        Account account = fine.getAccount();
        if (
            !shouldBlockAccount(account) ||
            account.isBlocked()
        ) return;

        blockAccount(account);
    }

    public void payAllFines(Account account) {
        // TODO: Actual paying integration
    }

    private boolean shouldBlockAccount(Account account) throws Exception {
        return this.retrieveCurrentBalanceInCents(account) > this.BLOCK_THRESHOLD_IN_CENTS;
    }

    private long retrieveCurrentBalanceInCents(Account account) throws Exception {
        return this.FINE_REPOSITORY.retrieveFineBalance(account)
            .orElseThrow()
            .getBalanceInCents();
    }

    private void blockAccount(Account account) throws Exception {
        this.ACCOUNT_REPOSITORY.blockAccount(account);
    }
}
