package com.github.pietergroenendijk.services.notifications.send_strategies;

import com.github.pietergroenendijk.services.notifications.notifiers.AppNotifier;
import com.github.pietergroenendijk.services.notifications.notifiers.EmailNotifier;
import com.github.pietergroenendijk.services.notifications.notifiers.Notifier;
import com.github.pietergroenendijk.services.notifications.notifiers.SMSNotifier;

public class AlertNotificationSendStrategy extends NotificationSendStrategy {
    public AlertNotificationSendStrategy(EmailNotifier emailNotifier, SMSNotifier smsNotifier, AppNotifier appNotifier) {
        super(new Notifier[]{
            emailNotifier,
            smsNotifier,
            appNotifier
        });
    }
}
