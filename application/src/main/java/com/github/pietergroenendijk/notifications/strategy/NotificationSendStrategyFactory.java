package com.github.pietergroenendijk.notifications.strategy;

import com.github.pietergroenendijk.notifications.notifiers.AppNotifier;
import com.github.pietergroenendijk.notifications.notifiers.EmailNotifier;
import com.github.pietergroenendijk.notifications.notifiers.SMSNotifier;

public class NotificationSendStrategyFactory {
    private final EmailNotifier emailNotifier;
    private final SMSNotifier smsNotifier;
    private final AppNotifier appNotifier;

    public NotificationSendStrategyFactory() {
        this(
            new EmailNotifier(),
            new SMSNotifier(),
            new AppNotifier()
        );
    }

    public NotificationSendStrategyFactory(EmailNotifier emailNotifier, SMSNotifier smsNotifier, AppNotifier appNotifier) {
        this.emailNotifier = emailNotifier;
        this.smsNotifier = smsNotifier;
        this.appNotifier = appNotifier;
    }

    public AlertNotificationSendStrategy createAlertStrategy() {
        return new AlertNotificationSendStrategy(
            emailNotifier,
            smsNotifier,
            appNotifier
        );
    }

    public WarningNotificationSendStrategy createWarningStrategy() {
        return new WarningNotificationSendStrategy(
            emailNotifier,
            appNotifier
        );
    }

    public ReminderNotificationSendStrategy createReminderStrategy() {
        return new ReminderNotificationSendStrategy(
            emailNotifier
        );
    }
}
