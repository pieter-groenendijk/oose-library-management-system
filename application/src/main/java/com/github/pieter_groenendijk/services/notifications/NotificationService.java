package com.github.pieter_groenendijk.services.notifications;

import com.github.pieter_groenendijk.TaskScheduler;
import com.github.pieter_groenendijk.model.Account;
import com.github.pieter_groenendijk.model.Lending;
import com.github.pieter_groenendijk.services.notifications.send_strategies.NotificationSendStrategyFactory;
import com.github.pieter_groenendijk.services.notifications.send_strategies.registry.NotificationSendStrategyRegistry;
import com.github.pieter_groenendijk.storage.notifications.NotificationTaskRepository;
import com.github.pieter_groenendijk.services.notifications.scheduling.NotificationTaskScheduler;
import com.github.pieter_groenendijk.services.notifications.task.NotificationTaskFactory;

public class NotificationService {
    private final NotificationTaskFactory TASK_FACTORY;
    private final NotificationTaskScheduler SCHEDULER;

    public NotificationService(TaskScheduler scheduler) {
        NotificationTaskRepository repository = new NotificationTaskRepository();

        this.TASK_FACTORY = new NotificationTaskFactory(repository);
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
            this.TASK_FACTORY.createReturnDateNotificationTask(account, lending)
        );
    }
}
