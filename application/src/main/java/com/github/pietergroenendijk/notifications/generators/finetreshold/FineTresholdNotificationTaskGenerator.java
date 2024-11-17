package com.github.pietergroenendijk.notifications.generators.finetreshold;

import com.github.pietergroenendijk.notifications.generators.*;
import com.github.pietergroenendijk.notifications.strategy.NotificationStrategy;

import java.time.LocalDateTime;

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
    protected LocalDateTime determineSendDateTime(FineTresholdNotificationTaskData data) {
        return null;
    }
}
