package com.github.pietergroenendijk.storage.notifications;

import com.github.pietergroenendijk.services.notifications.task.NotificationTask;

import java.time.LocalDateTime;

public interface INotificationTaskRepository {
    public NotificationTask[] retrieve(LocalDateTime scheduledUntil);
}
