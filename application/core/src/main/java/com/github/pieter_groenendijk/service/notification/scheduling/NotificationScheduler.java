package com.github.pieter_groenendijk.service.notification.scheduling;

import com.github.pieter_groenendijk.repository.scheduling.ITaskRepository;
import com.github.pieter_groenendijk.utils.scheduling.TaskScheduler;
import com.github.pieter_groenendijk.model.notification.Notification;
import com.github.pieter_groenendijk.service.notification.sendstrategies.registry.NotificationSendStrategyRegistry;
import com.github.pieter_groenendijk.utils.scheduling.longterm.LongTermTaskScheduler;

public class NotificationScheduler extends LongTermTaskScheduler<Notification> {
    private final NotificationSendStrategyRegistry SEND_STRATEGY_REGISTRY;

    public NotificationScheduler(
        ITaskRepository<Notification> notificationTaskRepository,
        TaskScheduler scheduler,
        NotificationSendStrategyRegistry sendStrategyRegistry
    ) {
        super(notificationTaskRepository, scheduler);

        this.SEND_STRATEGY_REGISTRY = sendStrategyRegistry;
    }

    public NotificationScheduler(
        ITaskRepository<Notification> notificationTaskRepository,
        int amountOfThreads,
        NotificationSendStrategyRegistry sendStrategyRegistry
    ) {
        super(notificationTaskRepository, amountOfThreads);

        this.SEND_STRATEGY_REGISTRY = sendStrategyRegistry;
    }

    @Override
    protected void executeTask(Notification task) {
        this.SEND_STRATEGY_REGISTRY
            .fromStrategyType(task.getSendStrategyType())
            .send(task);
    }
}
