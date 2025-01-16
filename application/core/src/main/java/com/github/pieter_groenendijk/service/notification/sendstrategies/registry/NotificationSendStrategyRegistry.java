package com.github.pieter_groenendijk.service.notification.sendstrategies.registry;


import com.github.pieter_groenendijk.service.notification.sendstrategies.*;

public class NotificationSendStrategyRegistry {
    private final AlertNotificationSendStrategy ALERT_STRATEGY;
    private final WarningNotificationSendStrategy WARNING_STRATEGY;
    private final ReminderNotificationSendStrategy REMINDER_STRATEGY;

    public NotificationSendStrategyRegistry(
        NotificationSendStrategyFactory factory
    ) {
        this.ALERT_STRATEGY = factory.createAlertStrategy();
        this.WARNING_STRATEGY = factory.createWarningStrategy();
        this.REMINDER_STRATEGY = factory.createReminderStrategy();
    }

    public NotificationSendStrategy fromStrategyType(SendStrategyType type) {
        return switch (type) {
            case ALERT -> ALERT_STRATEGY;
            case WARNING -> WARNING_STRATEGY;
            case REMINDER -> REMINDER_STRATEGY;
        };
    }
}
