package com.github.pietergroenendijk.notifications;

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
