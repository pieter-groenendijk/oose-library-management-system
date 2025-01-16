package com.github.pieter_groenendijk.service.notification.generators;

import com.github.pieter_groenendijk.model.Lending;
import com.github.pieter_groenendijk.model.notification.NotificationTask;
import com.github.pieter_groenendijk.repository.notification.INotificationTaskRepository;
import com.github.pieter_groenendijk.service.notification.send_strategies.registry.SendStrategyType;
import com.github.pieter_groenendijk.utils.scheduling.TaskStorage;

import java.time.LocalDateTime;

public class ReturnDateDetachedNotificationTaskGenerator extends DetachedNotificationTaskGenerator<Lending, NotificationTask> {
    public ReturnDateDetachedNotificationTaskGenerator(INotificationTaskRepository repository) {
        super(
            SendStrategyType.ALERT,
            repository
        );
    }

    @Override
    protected String generateTitle(Lending lending) {
        return "A product is due today!";
    }

    @Override
    protected String generateMessage(Lending lending) {
        return "Return the product to prevent a fine.";
    }

    @Override
    protected LocalDateTime determineScheduleDateTime(Lending lending) {
        return lending
            .mustReturnBy
            .atTime(8, 0);
    }

    @Override
    protected TaskStorage<NotificationTask> generateStorage(Lending lending) {
        return (NotificationTask task) -> {
            super.REPOSITORY.storeLendingAssociated(lending, task);
        };
    }

    @Override
    protected NotificationTask generateEmpty() {
        return new NotificationTask();
    }
}
