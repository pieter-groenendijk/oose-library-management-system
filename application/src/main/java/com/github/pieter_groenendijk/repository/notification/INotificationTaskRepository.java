package com.github.pieter_groenendijk.repository.notification;

import com.github.pieter_groenendijk.model.Lending;
import com.github.pieter_groenendijk.model.notification.NotificationTask;
import com.github.pieter_groenendijk.service.notification.task.NotificationTaskStatus;

import java.time.LocalDateTime;
import java.util.List;

public interface INotificationTaskRepository {
    List<NotificationTask> retrieve(LocalDateTime scheduledUntil);
    void storeLendingAssociated(Lending lending, NotificationTask task);
    void updateStatus(NotificationTask task, NotificationTaskStatus status);
}
