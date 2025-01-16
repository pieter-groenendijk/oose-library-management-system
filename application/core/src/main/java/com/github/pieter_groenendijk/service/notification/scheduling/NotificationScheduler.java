package com.github.pieter_groenendijk.service.notification.scheduling;

import com.github.pieter_groenendijk.repository.scheduling.ITaskRepository;
import com.github.pieter_groenendijk.utils.scheduling.TaskScheduler;
import com.github.pieter_groenendijk.model.notification.Notification;
import com.github.pieter_groenendijk.repository.notification.INotificationRepository;
import com.github.pieter_groenendijk.service.notification.sendstrategies.registry.NotificationSendStrategyRegistry;
import com.github.pieter_groenendijk.utils.scheduling.longterm.LongTermTaskScheduler;

import java.util.List;

public class NotificationScheduler extends LongTermTaskScheduler<Notification> {
    private final INotificationRepository REPOSITORY;
    private final NotificationSendStrategyRegistry SEND_STRATEGY_REGISTRY;

    public NotificationScheduler(
        INotificationRepository notificationTaskRepository,
        ITaskRepository repository,
        TaskScheduler scheduler,
        NotificationSendStrategyRegistry sendStrategyRegistry
    ) {
        super(repository, scheduler);

        this.REPOSITORY = notificationTaskRepository;
        this.SEND_STRATEGY_REGISTRY = sendStrategyRegistry;
    }

    public NotificationScheduler(
        INotificationRepository notificationTaskRepository,
        ITaskRepository repository,
        int amountOfThreads,
        NotificationSendStrategyRegistry sendStrategyRegistry
    ) {
        super(repository, amountOfThreads);

        this.REPOSITORY = notificationTaskRepository;
        this.SEND_STRATEGY_REGISTRY = sendStrategyRegistry;
    }


    @Override
    protected void executeTask(Notification task) {
        this.SEND_STRATEGY_REGISTRY
            .fromStrategyType(task.getSendStrategyType())
            .send(task);
    }

    @Override
    protected List<Notification> retrieveDueSoonTasks() {
        return this.REPOSITORY.retrieve(
            this.getScheduledUntilDateTime()
        );
    }
}
