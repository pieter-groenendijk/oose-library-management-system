package com.github.pieter_groenendijk.services.notifications.task;

import com.github.pieter_groenendijk.model.NotificationTask;

public interface INotificationTaskStorage {
    void store(NotificationTask task);
}
