package com.github.pietergroenendijk.services.notifications.task;

import com.github.pietergroenendijk.entities.Account;
import com.github.pietergroenendijk.entities.Lending;
import com.github.pietergroenendijk.storage.notifications.NotificationTaskRepository;
import com.github.pietergroenendijk.services.notifications.generators.ReturnDateNotificationTaskGenerator;

public class NotificationTaskFactory {
    private final ReturnDateNotificationTaskGenerator RETURN_DATE_TASK_GENERATOR;

    public NotificationTaskFactory(NotificationTaskRepository repository) {
        this.RETURN_DATE_TASK_GENERATOR = new ReturnDateNotificationTaskGenerator(repository);
    }

    public UnprocessedNotificationTask createReturnDateNotificationTask(Account account, Lending lending) {
        return RETURN_DATE_TASK_GENERATOR.generate(
            account,
            lending
        );
    }
}

