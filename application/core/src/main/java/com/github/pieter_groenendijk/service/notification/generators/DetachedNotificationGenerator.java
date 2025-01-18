package com.github.pieter_groenendijk.service.notification.generators;

import com.github.pieter_groenendijk.model.Account;
import com.github.pieter_groenendijk.model.notification.Notification;
import com.github.pieter_groenendijk.repository.notification.INotificationRepository;
import com.github.pieter_groenendijk.service.notification.sendstrategies.registry.SendStrategyType;
import com.github.pieter_groenendijk.utils.scheduling.TaskStorage;
import com.github.pieter_groenendijk.utils.scheduling.longterm.DetachedTask;

import java.time.LocalDateTime;

// TODO: Generalize generators into a superclass
public abstract class DetachedNotificationGenerator<T, K extends Notification> {
    protected final INotificationRepository REPOSITORY;

    private final SendStrategyType SEND_STRATEGY;

    protected DetachedNotificationGenerator(SendStrategyType sendStrategy, INotificationRepository repository) {
        this.SEND_STRATEGY = sendStrategy;
        this.REPOSITORY = repository;
    }

    public final DetachedTask<K> generate(Account account, T context) {
        return new DetachedTask<>(
            this.generateUnderlyingTask(account, context),
            this.generateStorage(context)
        );
    }

    protected abstract String generateTitle(T context);

    protected abstract String generateMessage(T context);

    protected abstract LocalDateTime determineScheduleDateTime(T context);

    protected abstract TaskStorage<K> generateStorage(T context);

    protected abstract K generateEmpty();

    private K generateUnderlyingTask(Account account, T context) {
        K task = this.generateEmpty();

        task.setTitle(this.generateTitle(context));
        task.setMessage(this.generateMessage(context));
        task.setAccount(account);
        task.setScheduledAt(this.determineScheduleDateTime(context));
        task.setSendStrategyType(this.SEND_STRATEGY);

        return task;
    }
}
