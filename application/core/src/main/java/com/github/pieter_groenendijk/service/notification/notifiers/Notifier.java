package com.github.pieter_groenendijk.service.notification.notifiers;

import com.github.pieter_groenendijk.model.notification.Notification;

public interface Notifier {
    void send(Notification task);
}
