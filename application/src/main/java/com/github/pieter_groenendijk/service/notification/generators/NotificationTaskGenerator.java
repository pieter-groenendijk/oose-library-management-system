package com.github.pieter_groenendijk.service.notification.generators;

import com.github.pieter_groenendijk.model.Account;
import com.github.pieter_groenendijk.model.notification.NotificationTask;
import com.github.pieter_groenendijk.repository.notification.INotificationTaskRepository;
import com.github.pieter_groenendijk.service.notification.send_strategies.registry.SendStrategyType;
import com.github.pieter_groenendijk.service.notification.task.INotificationTaskStorage;
import com.github.pieter_groenendijk.service.notification.task.UnprocessedNotificationTask;

import java.time.LocalDateTime;

public abstract class NotificationTaskGenerator<T> {
    protected final INotificationTaskRepository REPOSITORY;

    private final SendStrategyType SEND_STRATEGY;

    protected NotificationTaskGenerator(SendStrategyType sendStrategy, INotificationTaskRepository repository) {
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
