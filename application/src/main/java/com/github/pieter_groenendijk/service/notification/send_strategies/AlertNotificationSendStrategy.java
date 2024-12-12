package com.github.pieter_groenendijk.service.notification.send_strategies;


import com.github.pieter_groenendijk.service.notification.notifiers.AppNotifier;
import com.github.pieter_groenendijk.service.notification.notifiers.EmailNotifier;
import com.github.pieter_groenendijk.service.notification.notifiers.Notifier;
import com.github.pieter_groenendijk.service.notification.notifiers.SMSNotifier;

public class AlertNotificationSendStrategy extends NotificationSendStrategy {
    public AlertNotificationSendStrategy(EmailNotifier emailNotifier, SMSNotifier smsNotifier, AppNotifier appNotifier) {
        super(new Notifier[]{
            emailNotifier,
            smsNotifier,
            appNotifier
        });
    }
}
