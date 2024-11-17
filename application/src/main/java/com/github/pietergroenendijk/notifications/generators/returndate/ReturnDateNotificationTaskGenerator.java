package com.github.pietergroenendijk.notifications.generators.returndate;

import com.github.pietergroenendijk.notifications.generators.NotificationTaskGenerator;
import com.github.pietergroenendijk.notifications.strategy.NotificationStrategy;

import java.time.LocalDateTime;

public class ReturnDateNotificationTaskGenerator extends NotificationTaskGenerator<ReturnDateNotificationTaskData> {
    protected ReturnDateNotificationTaskGenerator(NotificationStrategy strategy) {
        super(strategy);
    }

    @Override
    protected String generateTitle(ReturnDateNotificationTaskData data) {
        return "A product is due today!";
    }

    @Override
    protected String generateMessage(ReturnDateNotificationTaskData data) {
        return "Return the product to prevent a fine.";
    }

    @Override
    protected LocalDateTime determineSendDateTime(ReturnDateNotificationTaskData data) {
        return data.START_OF_LENDING
                .plus(data.LENDING_DURATION)
                .withHour(8);
    }
}
