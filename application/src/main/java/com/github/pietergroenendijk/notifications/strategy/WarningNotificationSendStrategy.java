package com.github.pietergroenendijk.notifications.strategy;

import com.github.pietergroenendijk.notifications.notifiers.AppNotifier;
import com.github.pietergroenendijk.notifications.notifiers.EmailNotifier;
import com.github.pietergroenendijk.notifications.notifiers.Notifier;

public class WarningNotificationSendStrategy extends NotificationSendStrategy {
    public WarningNotificationSendStrategy(EmailNotifier emailNotifier, AppNotifier appNotifier) {
        super(new Notifier[]{
            emailNotifier,
            appNotifier
        });
    }
}
