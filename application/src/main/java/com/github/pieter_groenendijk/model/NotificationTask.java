package com.github.pieter_groenendijk.model;

import com.github.pieter_groenendijk.services.notifications.send_strategies.registry.SendStrategyType;

import java.time.LocalDateTime;

public class NotificationTask {
    public Long id;
    public Account account;

    public String title;
    public String message;
    public LocalDateTime scheduledAt;
    public SendStrategyType sendStrategyType;

    public NotificationTask(
        String title,
        String message,
        SendStrategyType sendStrategyType,
        Account account,
        LocalDateTime scheduledAt
    ) {
        this.title = title;
        this.message = message;
        this.sendStrategyType = sendStrategyType;
        this.account = account;
        this.scheduledAt = scheduledAt;
    }

    public boolean isScheduledBefore(LocalDateTime dateTime) {
        return this.scheduledAt.isBefore(dateTime);
    }
}
