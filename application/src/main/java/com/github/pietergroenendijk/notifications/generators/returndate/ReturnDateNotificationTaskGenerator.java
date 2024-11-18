package com.github.pietergroenendijk.notifications.generators.returndate;

import com.github.pietergroenendijk.notifications.generators.NotificationTaskGenerator;
import com.github.pietergroenendijk.notifications.strategy.NotificationStrategy;

import java.time.LocalDateTime;

public class ReturnDateNotificationTaskGenerator extends NotificationTaskGenerator<ReturnDateNotificationTaskContext> {
    public ReturnDateNotificationTaskGenerator(NotificationStrategy strategy) {
        super(strategy);
    }

    @Override
    protected String generateTitle(ReturnDateNotificationTaskContext context) {
        return "A product is due today!";
    }

    @Override
    protected String generateMessage(ReturnDateNotificationTaskContext context) {
        return "Return the product to prevent a fine.";
    }

    @Override
    protected LocalDateTime determineSendDateTime(ReturnDateNotificationTaskContext context) {
        return context.LENDING_START_DATETIME
                .plus(context.LENDING_DURATION)
                .withHour(8);
    }
}
