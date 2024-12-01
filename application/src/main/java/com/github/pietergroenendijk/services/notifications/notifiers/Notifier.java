package com.github.pietergroenendijk.services.notifications.notifiers;

import com.github.pietergroenendijk.services.notifications.task.NotificationTask;

public interface Notifier {
    void send(NotificationTask task);
}
