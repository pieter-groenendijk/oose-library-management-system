package com.github.pietergroenendijk.notifications.notifiers;

import com.github.pietergroenendijk.notifications.NotificationTask;

public interface Notifier {
    void send(NotificationTask task);
}
