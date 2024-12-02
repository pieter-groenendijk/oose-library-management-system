package com.github.pieter_groenendijk.services.notifications.task;

import com.github.pieter_groenendijk.model.NotificationTask;

public class UnprocessedNotificationTask {
    public final NotificationTask task;
    public final INotificationTaskStorage saver;

    public UnprocessedNotificationTask(
        NotificationTask task,
        INotificationTaskStorage saver
    ) {
        this.task = task;
        this.saver = saver;
    }

    public NotificationTask store() {
        this.saver.store(
            this.task
        );

        return this.task;
    }
}
