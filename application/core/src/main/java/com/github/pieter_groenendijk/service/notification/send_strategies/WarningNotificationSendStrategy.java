package com.github.pieter_groenendijk.service.notification.send_strategies;

import com.github.pieter_groenendijk.service.notification.notifiers.AppNotifier;
import com.github.pieter_groenendijk.service.notification.notifiers.EmailNotifier;
import com.github.pieter_groenendijk.service.notification.notifiers.Notifier;

public class WarningNotificationSendStrategy extends NotificationSendStrategy {
    public WarningNotificationSendStrategy(EmailNotifier emailNotifier, AppNotifier appNotifier) {
        super(new Notifier[]{
            emailNotifier,
            appNotifier
        });
    }
}
