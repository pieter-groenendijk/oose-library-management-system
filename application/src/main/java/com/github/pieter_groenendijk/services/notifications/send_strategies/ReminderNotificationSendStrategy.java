package com.github.pieter_groenendijk.services.notifications.send_strategies;

import com.github.pieter_groenendijk.services.notifications.notifiers.EmailNotifier;
import com.github.pieter_groenendijk.services.notifications.notifiers.Notifier;

public class ReminderNotificationSendStrategy extends NotificationSendStrategy {
    public ReminderNotificationSendStrategy(EmailNotifier emailNotifier) {
        super(new Notifier[]{
            emailNotifier
        });
    }
}
