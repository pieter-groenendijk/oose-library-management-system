package com.github.pietergroenendijk.notifications.strategy;

import com.github.pietergroenendijk.notifications.notifiers.EmailNotifier;
import com.github.pietergroenendijk.notifications.notifiers.Notifier;

public class ReminderNotificationSendStrategy extends NotificationSendStrategy {
    public ReminderNotificationSendStrategy(EmailNotifier emailNotifier) {
        super(new Notifier[]{
            emailNotifier
        });
    }
}
