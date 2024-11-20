package com.github.pietergroenendijk.notifications.strategy;

import com.github.pietergroenendijk.notifications.notifiers.AppNotifier;
import com.github.pietergroenendijk.notifications.notifiers.EmailNotifier;
import com.github.pietergroenendijk.notifications.notifiers.Notifier;
import com.github.pietergroenendijk.notifications.notifiers.SMSNotifier;

public class AlertNotificationSendStrategy extends NotificationSendStrategy {
    public AlertNotificationSendStrategy(EmailNotifier emailNotifier, SMSNotifier smsNotifier, AppNotifier appNotifier) {
        super(new Notifier[]{
            emailNotifier,
            smsNotifier,
            appNotifier
        });
    }
}
