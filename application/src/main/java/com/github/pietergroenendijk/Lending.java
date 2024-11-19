package com.github.pietergroenendijk;

import java.time.LocalDateTime;

public class Lending extends LendingBase {
    public final LocalDateTime STARTED_ON;
    public final LocalDateTime WILL_END_ON;

    public final AccountBase ACCOUNT;
    public final ProductBase PRODUCT;

    public Lending(
            long id,
            LocalDateTime startedOn,
            LocalDateTime willEndOn,
            AccountBase account,
            ProductBase product
        ) {
        super(id);

        this.STARTED_ON = startedOn;
        this.WILL_END_ON = willEndOn;
        this.ACCOUNT = account;
        this.PRODUCT = product;
    }
}
