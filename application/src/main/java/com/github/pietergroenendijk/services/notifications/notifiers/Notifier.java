package com.github.pietergroenendijk.services.notifications.notifiers;

import com.github.pietergroenendijk.entities.NotificationTask;

public interface Notifier {
    void send(NotificationTask task);
}
