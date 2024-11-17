package com.github.pietergroenendijk.notifications.generators.returndate;

import com.github.pietergroenendijk.notifications.generators.NotificationTaskContext;

import java.time.Duration;
import java.time.LocalDateTime;

public class ReturnDateNotificationTaskContext extends NotificationTaskContext {
    public final LocalDateTime START_OF_LENDING;
    public final Duration LENDING_DURATION;
    public final String PRODUCT_NAME;

    public ReturnDateNotificationTaskContext(int lendingId, LocalDateTime startOfLending, Duration lendingDuration, String productName) {
        super(lendingId);

        this.START_OF_LENDING = startOfLending;
        this.LENDING_DURATION = lendingDuration;
        this.PRODUCT_NAME = productName;
    }
}
