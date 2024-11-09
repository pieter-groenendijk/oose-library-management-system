package com.github.pietergroenendijk.notifications.strategy;

import com.github.pietergroenendijk.notifications.notifiers.AppNotifier;
import com.github.pietergroenendijk.notifications.notifiers.EmailNotifier;
import com.github.pietergroenendijk.notifications.notifiers.Notifier;
import com.github.pietergroenendijk.notifications.notifiers.SMSNotifier;

public class NotificationStrategyFactory {
    private final EmailNotifier emailNotifier = new EmailNotifier();
    private final SMSNotifier smsNotifier = new SMSNotifier();
    private final AppNotifier appNotifier = new AppNotifier();

    public AlertNotificationStrategy createAlertStrategy() {
        return new AlertNotificationStrategy(
            emailNotifier,
            smsNotifier,
            appNotifier
        );
    }

    public WarningNotificationStrategy createWarningStrategy() {
        return new WarningNotificationStrategy(
            emailNotifier,
            appNotifier
        );
    }

    public ReminderNotificationStrategy createReminderStrategy() {
        return new ReminderNotificationStrategy(
            emailNotifier
        );
    }
}
