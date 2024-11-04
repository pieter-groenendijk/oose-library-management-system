package com.github.pietergroenendijk.notifications;

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
