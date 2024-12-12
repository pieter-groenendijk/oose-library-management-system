package com.github.pieter_groenendijk.service.notification.send_strategies.registry;


import com.github.pieter_groenendijk.service.notification.send_strategies.*;

public class NotificationSendStrategyRegistry {
    private final AlertNotificationSendStrategy alertNotificationSendStrategy;
    private final WarningNotificationSendStrategy warningNotificationSendStrategy;
    private final ReminderNotificationSendStrategy reminderNotificationSendStrategy;

    public NotificationSendStrategyRegistry(
        NotificationSendStrategyFactory factory
    ) {
        this.alertNotificationSendStrategy = factory.createAlertStrategy();
        this.warningNotificationSendStrategy = factory.createWarningStrategy();
        this.reminderNotificationSendStrategy = factory.createReminderStrategy();
    }

    public NotificationSendStrategy fromStrategyType(SendStrategyType type) {
        return switch (type) {
            case ALERT -> alertNotificationSendStrategy;
            case WARNING -> warningNotificationSendStrategy;
            case REMINDER -> reminderNotificationSendStrategy;
        };
    }
}
