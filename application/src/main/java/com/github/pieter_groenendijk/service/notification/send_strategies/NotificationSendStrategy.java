package com.github.pieter_groenendijk.service.notification.send_strategies;

import com.github.pieter_groenendijk.model.notification.NotificationTask;
import com.github.pieter_groenendijk.service.notification.notifiers.Notifier;

public class NotificationSendStrategy implements Notifier {
    private final Notifier[] channels;

    public NotificationSendStrategy(Notifier[] channels) {
        this.channels = channels;
    }

    @Override
    public void send(NotificationTask task) {
        for (Notifier channel : channels) {
            channel.send(task);
        }
    }
}
