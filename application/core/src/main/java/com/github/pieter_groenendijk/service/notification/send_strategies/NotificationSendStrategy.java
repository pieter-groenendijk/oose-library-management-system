package com.github.pieter_groenendijk.service.notification.send_strategies;

import com.github.pieter_groenendijk.model.notification.NotificationTask;
import com.github.pieter_groenendijk.service.notification.notifiers.Notifier;

public class NotificationSendStrategy implements Notifier {
    private final Notifier[] NOTIFIERS;

    public NotificationSendStrategy(Notifier[] notifiers) {
        this.NOTIFIERS = notifiers;
    }

    @Override
    public void send(NotificationTask task) {
        for (Notifier notifier : NOTIFIERS) {
            notifier.send(task);
        }
    }
}
