package com.github.pietergroenendijk.notifications;

import com.github.pietergroenendijk.notifications.strategy.NotificationStrategy;

import java.time.LocalDateTime;

public record NotificationTask(
        Notification notification,
        NotificationStrategy strategy,
        NotificationTaskStoreStrategy storeStrategy,
        java.time.LocalDateTime scheduledDateTime,
        UserContactDetails contactDetails
){
    public boolean isScheduledBefore(LocalDateTime dateTime) {
        return this.scheduledDateTime.isBefore(dateTime);
    }

    public void store() {
        storeStrategy.store(this);
    }

    public void send() {
        this.strategy.send(this);
    }
}
