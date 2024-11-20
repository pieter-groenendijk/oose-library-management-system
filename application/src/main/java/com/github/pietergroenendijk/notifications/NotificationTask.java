package com.github.pietergroenendijk.notifications;

import com.github.pietergroenendijk.AccountBase;
import com.github.pietergroenendijk.notifications.strategy.NotificationSendStrategy;

import java.time.LocalDateTime;

public class NotificationTask
{
    public final Notification NOTIFICATION;
    public final NotificationSendStrategy SEND_STRATEGY;
    public final NotificationTaskStoreStrategy STORE_STRATEGY;
    public final LocalDateTime SCHEDULED_AT;
    public final AccountBase ACCOUNT;

    public NotificationTask(
        Notification notification,
        NotificationSendStrategy sendStrategy,
        NotificationTaskStoreStrategy storeStrategy,
        java.time.LocalDateTime scheduledAt,
        AccountBase account
    ) {
        this.NOTIFICATION = notification;
        this.SEND_STRATEGY = sendStrategy;
        this.STORE_STRATEGY = storeStrategy;
        this.SCHEDULED_AT = scheduledAt;
        this.ACCOUNT = account;
    }

    public boolean isScheduledBefore(LocalDateTime dateTime) {
        return this.SCHEDULED_AT.isBefore(dateTime);
    }

    public void store() {
        this.STORE_STRATEGY.store(this);
    }

    public void send() {
        this.SEND_STRATEGY.send(this);
    }
}
