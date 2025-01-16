package com.github.pieter_groenendijk.service.notification.generators;

import com.github.pieter_groenendijk.model.Account;
import com.github.pieter_groenendijk.model.notification.NotificationTask;
import com.github.pieter_groenendijk.repository.notification.INotificationTaskRepository;
import com.github.pieter_groenendijk.service.notification.send_strategies.registry.SendStrategyType;
import com.github.pieter_groenendijk.utils.scheduling.TaskStorage;
import com.github.pieter_groenendijk.utils.scheduling.longterm.DetachedTask;

import java.time.LocalDateTime;

// TODO: Generalize generators into a superclass
public abstract class DetachedNotificationTaskGenerator<T, K extends NotificationTask> {
    protected final INotificationTaskRepository REPOSITORY;

    private final SendStrategyType SEND_STRATEGY;

    protected DetachedNotificationTaskGenerator(SendStrategyType sendStrategy, INotificationTaskRepository repository) {
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
