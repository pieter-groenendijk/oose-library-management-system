package com.github.pieter_groenendijk.service.notification.send_strategies;


import com.github.pieter_groenendijk.service.notification.notifiers.EmailNotifier;
import com.github.pieter_groenendijk.service.notification.notifiers.Notifier;

public class ReminderNotificationSendStrategy extends NotificationSendStrategy {
    public ReminderNotificationSendStrategy(EmailNotifier emailNotifier) {
        super(new Notifier[]{
            emailNotifier
        });
    }
}
