package com.github.pietergroenendijk.notifications.generators.returndate;

import java.time.Duration;
import java.time.LocalDateTime;

public class ReturnDateNotificationTaskData {
    public final int LENDING_ID;
    public final LocalDateTime START_OF_LENDING;
    public final Duration LENDING_DURATION;
    public final String PRODUCT_NAME;

    public ReturnDateNotificationTaskData(int lendingId, LocalDateTime startOfLending, Duration lendingDuration, String productName) {
        this.LENDING_ID = lendingId;
        this.START_OF_LENDING = startOfLending;
        this.LENDING_DURATION = lendingDuration;
        this.PRODUCT_NAME = productName;
    }
}
