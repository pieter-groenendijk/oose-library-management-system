package com.github.pietergroenendijk.notifications.strategy;

import com.github.pietergroenendijk.notifications.NotificationTask;
import com.github.pietergroenendijk.notifications.notifiers.Notifier;

public class NotificationStrategy implements Notifier {
    private final Notifier[] channels;

    public NotificationStrategy(Notifier[] channels) {
        this.channels = channels;
    }

    @Override
    public void send(NotificationTask task) {
        for (Notifier channel : channels) {
            channel.send(task);
        }
    }
}
