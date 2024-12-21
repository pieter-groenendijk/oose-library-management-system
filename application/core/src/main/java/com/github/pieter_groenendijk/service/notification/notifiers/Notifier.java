package com.github.pieter_groenendijk.service.notification.notifiers;

import com.github.pieter_groenendijk.model.notification.NotificationTask;

public interface Notifier {
    void send(NotificationTask task);
}
