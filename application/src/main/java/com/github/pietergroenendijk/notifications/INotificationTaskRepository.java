package com.github.pietergroenendijk.notifications;

import java.time.LocalDateTime;

public interface INotificationTaskRepository {
    public NotificationTask[] retrieve(LocalDateTime scheduledUntil);
}
