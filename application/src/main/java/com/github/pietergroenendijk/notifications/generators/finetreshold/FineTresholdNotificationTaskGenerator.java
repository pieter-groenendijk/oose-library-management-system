package com.github.pietergroenendijk.notifications.generators.finetreshold;

import com.github.pietergroenendijk.notifications.generators.*;
import com.github.pietergroenendijk.notifications.strategy.NotificationStrategy;

import java.time.LocalTime;

public class FineTresholdNotificationTaskGenerator extends NotificationTaskGenerator<FineTresholdNotificationTaskData> {
    protected FineTresholdNotificationTaskGenerator(NotificationStrategy strategy) {
        super(strategy);
    }

    @Override
    protected String generateTitle(FineTresholdNotificationTaskData data) {
        return "";
    }

    @Override
    protected String generateMessage(FineTresholdNotificationTaskData data) {
        return "";
    }

    @Override
    protected LocalTime determineSendDateTime(FineTresholdNotificationTaskData data) {
        return null;
    }
}
