package com.github.pieter_groenendijk.service.notification;

import com.github.pieter_groenendijk.utils.scheduling.TaskScheduler;
import com.github.pieter_groenendijk.model.Account;
import com.github.pieter_groenendijk.model.Lending;
import com.github.pieter_groenendijk.repository.notification.INotificationTaskRepository;
import com.github.pieter_groenendijk.service.notification.scheduling.NotificationTaskScheduler;
import com.github.pieter_groenendijk.service.notification.send_strategies.NotificationSendStrategyFactory;
import com.github.pieter_groenendijk.service.notification.send_strategies.registry.NotificationSendStrategyRegistry;
import com.github.pieter_groenendijk.service.notification.task.NotificationTaskFactory;

public class NotificationService {
    private final NotificationTaskFactory FACTORY;
    private final NotificationTaskScheduler SCHEDULER;

    public NotificationService(TaskScheduler scheduler, INotificationTaskRepository repository) {
        this.FACTORY = new NotificationTaskFactory(repository);
        this.SCHEDULER = new NotificationTaskScheduler(
            scheduler,
            repository,
            new NotificationSendStrategyRegistry(
                new NotificationSendStrategyFactory()
            )
        );
    }

    public void scheduleNewLendingBundle(Account account, Lending lending) {
        this.scheduleReturnDateNotification(account, lending);
    }

    private void scheduleReturnDateNotification(Account account, Lending lending) {
        this.SCHEDULER.schedule(
            this.FACTORY.createReturnDateNotificationTask(account, lending)
        );
    }
}
