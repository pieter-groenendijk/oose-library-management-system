package com.github.pieter_groenendijk.services.notifications.task;

import com.github.pieter_groenendijk.model.Account;
import com.github.pieter_groenendijk.model.Lending;
import com.github.pieter_groenendijk.services.notifications.generators.ReturnDateNotificationTaskGenerator;
import com.github.pieter_groenendijk.storage.notifications.INotificationTaskRepository;

public class NotificationTaskFactory {
    private final ReturnDateNotificationTaskGenerator RETURN_DATE_TASK_GENERATOR;

    public NotificationTaskFactory(INotificationTaskRepository repository) {
        this.RETURN_DATE_TASK_GENERATOR = new ReturnDateNotificationTaskGenerator(repository);
    }

    public UnprocessedNotificationTask createReturnDateNotificationTask(Account account, Lending lending) {
        return RETURN_DATE_TASK_GENERATOR.generate(
            account,
            lending
        );
    }
}

