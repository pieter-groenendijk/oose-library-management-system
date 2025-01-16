package com.github.pieter_groenendijk.service.notification.task;

import com.github.pieter_groenendijk.model.Account;
import com.github.pieter_groenendijk.model.Lending;
import com.github.pieter_groenendijk.model.notification.Notification;
import com.github.pieter_groenendijk.repository.notification.INotificationRepository;
import com.github.pieter_groenendijk.service.notification.generators.OverdueNotificationGenerator;
import com.github.pieter_groenendijk.utils.scheduling.longterm.DetachedTask;

public class DetachedNotificationFactory {
    private final OverdueNotificationGenerator OVERDUE_GENERATOR;

    public DetachedNotificationFactory(INotificationRepository repository) {
        this.OVERDUE_GENERATOR = new OverdueNotificationGenerator(repository);
    }

    public DetachedTask<Notification> createReturnDateNotificationTask(Account account, Lending lending) {
        return OVERDUE_GENERATOR.generate(
            account,
            lending
        );
    }
}

