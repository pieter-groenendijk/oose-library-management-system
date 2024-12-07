package com.github.pieter_groenendijk.services.notifications.generators;

import com.github.pieter_groenendijk.model.Account;
import com.github.pieter_groenendijk.model.NotificationTask;
import com.github.pieter_groenendijk.services.notifications.send_strategies.registry.SendStrategyType;
import com.github.pieter_groenendijk.services.notifications.task.INotificationTaskStorage;
import com.github.pieter_groenendijk.services.notifications.task.UnprocessedNotificationTask;
import com.github.pieter_groenendijk.storage.notifications.NotificationTaskRepository;

import java.time.LocalDateTime;

public abstract class NotificationTaskGenerator<T> {
    protected final NotificationTaskRepository REPOSITORY;

    private final SendStrategyType SEND_STRATEGY;

    protected NotificationTaskGenerator(SendStrategyType sendStrategy, NotificationTaskRepository repository) {
        this.SEND_STRATEGY = sendStrategy;
        this.REPOSITORY = repository;
    }

    public final UnprocessedNotificationTask generate(Account account, T context) {
        return new UnprocessedNotificationTask(
            this.generateUnderlyingTask(account, context),
            this.generateStorage(context)
        );
    }

    protected abstract String generateTitle(T context);

    protected abstract String generateMessage(T context);

    protected abstract LocalDateTime determineScheduleDateTime(T context);

    protected abstract INotificationTaskStorage generateStorage(T context);

    private NotificationTask generateUnderlyingTask(Account account, T context) {
        return new NotificationTask(
            this.generateTitle(context),
            this.generateMessage(context),
            account,
            this.determineScheduleDateTime(context),
            this.SEND_STRATEGY
        );
    }
}
