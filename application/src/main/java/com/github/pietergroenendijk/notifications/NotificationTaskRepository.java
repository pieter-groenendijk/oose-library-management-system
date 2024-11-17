package com.github.pietergroenendijk.notifications;

import java.time.LocalDateTime;

public class NotificationTaskRepository implements INotificationTaskRepository {
    public NotificationTaskRepository() {

    }

    @Override
    public NotificationTask[] retrieve(LocalDateTime scheduledUntil) {
        return new NotificationTask[]{};
    }

    public void store(NotificationTask task) {

    }
}
