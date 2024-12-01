package com.github.pietergroenendijk.storage.notifications;

import com.github.pietergroenendijk.entities.NotificationTask;

import java.time.LocalDateTime;

public interface INotificationTaskRepository {
    public NotificationTask[] retrieve(LocalDateTime scheduledUntil);
}
