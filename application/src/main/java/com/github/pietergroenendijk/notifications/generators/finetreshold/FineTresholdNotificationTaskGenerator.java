package com.github.pietergroenendijk.notifications.generators.finetreshold;

import com.github.pietergroenendijk.notifications.generators.*;
import com.github.pietergroenendijk.notifications.strategy.NotificationStrategy;

import java.time.LocalDateTime;

public class FineTresholdNotificationTaskGenerator extends NotificationTaskGenerator<FineThresholdNotificationTaskContext> {
    public FineTresholdNotificationTaskGenerator(NotificationStrategy strategy) {
        super(strategy);
    }

    @Override
    protected String generateTitle(FineThresholdNotificationTaskContext data) {
        return "";
    }

    @Override
    protected String generateMessage(FineThresholdNotificationTaskContext data) {
        return "";
    }

    @Override
    protected LocalDateTime determineSendDateTime(FineThresholdNotificationTaskContext data) {
        return null;
    }
}
