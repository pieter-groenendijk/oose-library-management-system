package com.github.pieter_groenendijk.service.notification.task;

import com.github.pieter_groenendijk.model.Account;
import com.github.pieter_groenendijk.model.Lending;
import com.github.pieter_groenendijk.model.notification.NotificationTask;
import com.github.pieter_groenendijk.repository.notification.INotificationTaskRepository;
import com.github.pieter_groenendijk.service.notification.generators.ReturnDateDetachedNotificationTaskGenerator;
import com.github.pieter_groenendijk.utils.scheduling.longterm.DetachedTask;

public class DetachedNotificationTaskFactory {
    private final ReturnDateDetachedNotificationTaskGenerator RETURN_DATE_TASK_GENERATOR;

    public DetachedNotificationTaskFactory(INotificationTaskRepository repository) {
        this.RETURN_DATE_TASK_GENERATOR = new ReturnDateDetachedNotificationTaskGenerator(repository);
    }

    public DetachedTask<NotificationTask> createReturnDateNotificationTask(Account account, Lending lending) {
        return RETURN_DATE_TASK_GENERATOR.generate(
            account,
            lending
        );
    }
}

