package com.github.pietergroenendijk.storage.notifications;

import com.github.pietergroenendijk.entities.Lending;
import com.github.pietergroenendijk.entities.NotificationTask;

import java.time.LocalDateTime;

public class NotificationTaskRepository implements INotificationTaskRepository {
    public NotificationTaskRepository() {

    }

    @Override
    public NotificationTask[] retrieve(LocalDateTime scheduledUntil) {
        return new NotificationTask[]{};
    }

    public void storeLendingAssociated(Lending lending, NotificationTask task) {
        // TODO insert using a view, so that there only one statement is sent, as to minimize latency.
    }

    public void markCompleted(NotificationTask task) {
        // TODO mark completed in database
    }
}
