package com.github.pietergroenendijk.services.notifications.task;

import com.github.pietergroenendijk.entities.NotificationTask;

public interface INotificationTaskStorage {
    void store(NotificationTask task);
}
