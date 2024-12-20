package com.github.pieter_groenendijk.service.notification.send_strategies;

import com.github.pieter_groenendijk.service.notification.notifiers.AppNotifier;
import com.github.pieter_groenendijk.service.notification.notifiers.EmailNotifier;
import com.github.pieter_groenendijk.service.notification.notifiers.SMSNotifier;

public class NotificationSendStrategyFactory {
    private final EmailNotifier EMAIL_NOTIFIER;
    private final SMSNotifier SMS_NOTIFIER;
    private final AppNotifier APP_NOTIFIER;

    public NotificationSendStrategyFactory() {
        this(
            new EmailNotifier(),
            new SMSNotifier(),
            new AppNotifier()
        );
    }

    public NotificationSendStrategyFactory(EmailNotifier emailNotifier, SMSNotifier smsNotifier, AppNotifier appNotifier) {
        this.EMAIL_NOTIFIER = emailNotifier;
        this.SMS_NOTIFIER = smsNotifier;
        this.APP_NOTIFIER = appNotifier;
    }

    public AlertNotificationSendStrategy createAlertStrategy() {
        return new AlertNotificationSendStrategy(
            EMAIL_NOTIFIER,
            SMS_NOTIFIER,
            APP_NOTIFIER
        );
    }

    public WarningNotificationSendStrategy createWarningStrategy() {
        return new WarningNotificationSendStrategy(
            EMAIL_NOTIFIER,
            APP_NOTIFIER
        );
    }

    public ReminderNotificationSendStrategy createReminderStrategy() {
        return new ReminderNotificationSendStrategy(
            EMAIL_NOTIFIER
        );
    }
}
