package com.github.pieter_groenendijk.services.notifications.send_strategies;

import com.github.pieter_groenendijk.services.notifications.notifiers.AppNotifier;
import com.github.pieter_groenendijk.services.notifications.notifiers.EmailNotifier;
import com.github.pieter_groenendijk.services.notifications.notifiers.Notifier;
import com.github.pieter_groenendijk.services.notifications.notifiers.SMSNotifier;

public class AlertNotificationSendStrategy extends NotificationSendStrategy {
    public AlertNotificationSendStrategy(EmailNotifier emailNotifier, SMSNotifier smsNotifier, AppNotifier appNotifier) {
        super(new Notifier[]{
            emailNotifier,
            smsNotifier,
            appNotifier
        });
    }
}
