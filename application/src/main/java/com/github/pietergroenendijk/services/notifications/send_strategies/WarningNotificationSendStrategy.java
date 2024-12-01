package com.github.pietergroenendijk.services.notifications.send_strategies;

import com.github.pietergroenendijk.services.notifications.notifiers.AppNotifier;
import com.github.pietergroenendijk.services.notifications.notifiers.EmailNotifier;
import com.github.pietergroenendijk.services.notifications.notifiers.Notifier;

public class WarningNotificationSendStrategy extends NotificationSendStrategy {
    public WarningNotificationSendStrategy(EmailNotifier emailNotifier, AppNotifier appNotifier) {
        super(new Notifier[]{
            emailNotifier,
            appNotifier
        });
    }
}
