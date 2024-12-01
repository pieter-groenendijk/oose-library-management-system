package com.github.pietergroenendijk.services.notifications.send_strategies;

import com.github.pietergroenendijk.services.notifications.notifiers.EmailNotifier;
import com.github.pietergroenendijk.services.notifications.notifiers.Notifier;

public class ReminderNotificationSendStrategy extends NotificationSendStrategy {
    public ReminderNotificationSendStrategy(EmailNotifier emailNotifier) {
        super(new Notifier[]{
            emailNotifier
        });
    }
}
