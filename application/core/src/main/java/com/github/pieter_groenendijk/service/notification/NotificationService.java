package com.github.pieter_groenendijk.service.notification;

import com.github.pieter_groenendijk.hibernate.SessionFactoryFactory;
import com.github.pieter_groenendijk.repository.scheduling.TaskRepository;
import com.github.pieter_groenendijk.utils.scheduling.TaskScheduler;
import com.github.pieter_groenendijk.model.Account;
import com.github.pieter_groenendijk.model.Lending;
import com.github.pieter_groenendijk.repository.notification.INotificationTaskRepository;
import com.github.pieter_groenendijk.service.notification.scheduling.NotificationTaskScheduler;
import com.github.pieter_groenendijk.service.notification.sendstrategies.NotificationSendStrategyFactory;
import com.github.pieter_groenendijk.service.notification.sendstrategies.registry.NotificationSendStrategyRegistry;
import com.github.pieter_groenendijk.service.notification.task.DetachedNotificationTaskFactory;

public class NotificationService {
    private final DetachedNotificationTaskFactory FACTORY;
    private final NotificationTaskScheduler SCHEDULER;

    public NotificationService(
        TaskScheduler scheduler,
        INotificationTaskRepository repository
    ) {
        this.FACTORY = new DetachedNotificationTaskFactory(repository);
        this.SCHEDULER = new NotificationTaskScheduler(
            repository,
            new TaskRepository(new SessionFactoryFactory().create()),
            scheduler,
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
