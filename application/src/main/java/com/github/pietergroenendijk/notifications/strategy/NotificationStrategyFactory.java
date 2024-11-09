package com.github.pietergroenendijk.notifications.strategy;

import com.github.pietergroenendijk.notifications.notifiers.AppNotifier;
import com.github.pietergroenendijk.notifications.notifiers.EmailNotifier;
import com.github.pietergroenendijk.notifications.notifiers.Notifier;
import com.github.pietergroenendijk.notifications.notifiers.SMSNotifier;

public class NotificationStrategyFactory {
    private final EmailNotifier emailNotifier;
    private final SMSNotifier smsNotifier;
    private final AppNotifier appNotifier;

    public NotificationStrategyFactory() {
        this(
            new EmailNotifier(),
            new SMSNotifier(),
            new AppNotifier()
        );
    }

    public NotificationStrategyFactory(EmailNotifier emailNotifier, SMSNotifier smsNotifier, AppNotifier appNotifier) {
        this.emailNotifier = emailNotifier;
        this.smsNotifier = smsNotifier;
        this.appNotifier = appNotifier;
    }

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
