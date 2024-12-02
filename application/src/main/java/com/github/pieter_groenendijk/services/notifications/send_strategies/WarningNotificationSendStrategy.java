package com.github.pieter_groenendijk.services.notifications.send_strategies;

import com.github.pieter_groenendijk.services.notifications.notifiers.AppNotifier;
import com.github.pieter_groenendijk.services.notifications.notifiers.EmailNotifier;
import com.github.pieter_groenendijk.services.notifications.notifiers.Notifier;

public class WarningNotificationSendStrategy extends NotificationSendStrategy {
    public WarningNotificationSendStrategy(EmailNotifier emailNotifier, AppNotifier appNotifier) {
        super(new Notifier[]{
            emailNotifier,
            appNotifier
        });
    }
}
