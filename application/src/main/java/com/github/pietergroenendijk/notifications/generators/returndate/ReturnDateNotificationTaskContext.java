package com.github.pietergroenendijk.notifications.generators.returndate;

import java.time.Duration;
import java.time.LocalDateTime;

public class ReturnDateNotificationTaskContext {
    public final LocalDateTime LENDING_START_DATETIME;
    public final Duration LENDING_DURATION;
    public final String LENT_PRODUCT_NAME;

    public ReturnDateNotificationTaskContext(LocalDateTime lendingStartDateTime, Duration lendingDuration, String lentProductName) {
        this.LENDING_START_DATETIME = lendingStartDateTime;
        this.LENDING_DURATION = lendingDuration;
        this.LENT_PRODUCT_NAME = lentProductName;
    }
}
