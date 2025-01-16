package com.github.pieter_groenendijk.repository.notification;

import com.github.pieter_groenendijk.model.notification.Notification;

import java.time.LocalDateTime;
import java.util.List;

public interface INotificationTaskRepository {
    List<Notification> retrieve(LocalDateTime scheduledUntil);
}
