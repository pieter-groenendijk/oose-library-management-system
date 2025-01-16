package com.github.pieter_groenendijk.service.notification;

import com.github.pieter_groenendijk.hibernate.SessionFactoryFactory;
import com.github.pieter_groenendijk.repository.scheduling.TaskRepository;
import com.github.pieter_groenendijk.utils.scheduling.TaskScheduler;
import com.github.pieter_groenendijk.model.Account;
import com.github.pieter_groenendijk.model.Lending;
import com.github.pieter_groenendijk.repository.notification.INotificationRepository;
import com.github.pieter_groenendijk.service.notification.scheduling.NotificationScheduler;
import com.github.pieter_groenendijk.service.notification.sendstrategies.NotificationSendStrategyFactory;
import com.github.pieter_groenendijk.service.notification.sendstrategies.registry.NotificationSendStrategyRegistry;
import com.github.pieter_groenendijk.service.notification.task.DetachedNotificationFactory;

public class NotificationService {
    private final DetachedNotificationFactory FACTORY;
    private final NotificationScheduler SCHEDULER;

    public NotificationService(
        TaskScheduler scheduler,
        INotificationRepository repository
    ) {
        this.FACTORY = new DetachedNotificationFactory(repository);
        this.SCHEDULER = new NotificationScheduler(
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
