package com.github.pieter_groenendijk.service.notification.sendstrategies;

import com.github.pieter_groenendijk.model.notification.Notification;
import com.github.pieter_groenendijk.service.notification.notifiers.Notifier;

public class NotificationSendStrategy implements Notifier {
    private final Notifier[] NOTIFIERS;

    public NotificationSendStrategy(Notifier[] notifiers) {
        this.NOTIFIERS = notifiers;
    }

    @Override
    public void send(Notification task) {
        for (Notifier notifier : NOTIFIERS) {
            notifier.send(task);
        }
    }
}
