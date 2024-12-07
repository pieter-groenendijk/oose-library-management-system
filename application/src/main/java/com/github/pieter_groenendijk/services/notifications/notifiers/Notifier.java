package com.github.pieter_groenendijk.services.notifications.notifiers;

import com.github.pieter_groenendijk.model.NotificationTask;

public interface Notifier {
    void send(NotificationTask task);
}
