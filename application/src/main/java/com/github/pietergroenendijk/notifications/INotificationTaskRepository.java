package com.github.pietergroenendijk.notifications;

public interface INotificationTaskRepository {
    public NotificationTask[] retrieveScheduledTasks();
}
