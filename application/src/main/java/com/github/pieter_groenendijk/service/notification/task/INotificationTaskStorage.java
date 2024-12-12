package com.github.pieter_groenendijk.service.notification.task;

import com.github.pieter_groenendijk.model.notification.NotificationTask;

public interface INotificationTaskStorage {
    void store(NotificationTask task);
}
