package com.github.pieter_groenendijk.storage.notifications;

import com.github.pieter_groenendijk.model.NotificationTask;

import java.time.LocalDateTime;

public interface INotificationTaskRepository {
    public NotificationTask[] retrieve(LocalDateTime scheduledUntil);
}
