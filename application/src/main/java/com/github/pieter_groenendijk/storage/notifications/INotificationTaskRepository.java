package com.github.pieter_groenendijk.storage.notifications;

import com.github.pieter_groenendijk.model.Lending;
import com.github.pieter_groenendijk.model.NotificationTask;

import java.time.LocalDateTime;
import java.util.List;

public interface INotificationTaskRepository {
    List<NotificationTask> retrieve(LocalDateTime scheduledUntil);
    void storeLendingAssociated(Lending lending, NotificationTask task);
}
