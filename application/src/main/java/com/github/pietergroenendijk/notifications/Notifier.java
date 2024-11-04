package com.github.pietergroenendijk.notifications;

public interface Notifier {
    void send(NotificationTask task);
}
