package com.github.pietergroenendijk.services.notifications.send_strategies;

import com.github.pietergroenendijk.services.notifications.task.NotificationTask;
import com.github.pietergroenendijk.services.notifications.notifiers.Notifier;

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
