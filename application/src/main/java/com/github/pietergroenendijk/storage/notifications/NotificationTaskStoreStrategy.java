package com.github.pietergroenendijk.storage.notifications;

import com.github.pietergroenendijk.services.notifications.task.NotificationTask;

public interface NotificationTaskStoreStrategy {
    public void store(NotificationTask task);
}
