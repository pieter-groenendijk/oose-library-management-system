package com.github.pieter_groenendijk.storage.notifications;

import com.github.pieter_groenendijk.model.NotificationTask;

import java.time.LocalDateTime;
import java.util.List;

public interface INotificationTaskRepository {
    public List<NotificationTask> retrieve(LocalDateTime scheduledUntil);
}
