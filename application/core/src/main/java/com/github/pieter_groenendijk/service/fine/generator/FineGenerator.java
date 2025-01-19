package com.github.pieter_groenendijk.service.fine.generator;

import com.github.pieter_groenendijk.model.Account;
import com.github.pieter_groenendijk.model.fine.Fine;
import com.github.pieter_groenendijk.model.fine.FineType;

public abstract class FineGenerator<Association, AssociatedFine extends Fine> {
    private final FineType TYPE;

    protected FineGenerator(FineType type) {
        this.TYPE = type;
    }

    public AssociatedFine generate(Account account, Association association) {
        AssociatedFine fine = this.generateEmpty();

        fine.setFineType(this.TYPE);
        fine.setAmountInCents(this.determineAmountInCents());
        fine.setAccount(account);
        this.setFineAssociation(fine, association);

        return fine;
    }

    protected abstract AssociatedFine generateEmpty();

    protected abstract void setFineAssociation(AssociatedFine fine, Association association);

    // TODO: Make it update every so often, so that when the amount is changed, this will update too eventually.
    protected Long determineAmountInCents() {
        return this.TYPE.getAmountInCents();
    }
}
