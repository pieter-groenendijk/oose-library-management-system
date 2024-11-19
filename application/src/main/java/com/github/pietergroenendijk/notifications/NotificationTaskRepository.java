package com.github.pietergroenendijk.notifications;

import com.github.pietergroenendijk.LendingBase;

import java.time.LocalDateTime;

public class NotificationTaskRepository implements INotificationTaskRepository {
    public NotificationTaskRepository() {

    }

    @Override
    public NotificationTask[] retrieve(LocalDateTime scheduledUntil) {
        return new NotificationTask[]{};
    }

    public void storeLendingAssociated(LendingBase lending, NotificationTask task) {
        // TODO insert using a view, so that there only one statement is sent, as to minimize latency.
    }
}
