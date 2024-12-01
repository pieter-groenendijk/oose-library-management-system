package com.github.pietergroenendijk.services.notifications.generators;

import com.github.pietergroenendijk.entities.Lending;
import com.github.pietergroenendijk.entities.NotificationTask;
import com.github.pietergroenendijk.services.notifications.send_strategies.registry.SendStrategyType;
import com.github.pietergroenendijk.services.notifications.task.INotificationTaskStorage;
import com.github.pietergroenendijk.storage.notifications.NotificationTaskRepository;

import java.time.LocalDateTime;

public class ReturnDateNotificationTaskGenerator extends NotificationTaskGenerator<Lending> {
    public ReturnDateNotificationTaskGenerator(NotificationTaskRepository repository) {
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
        return lending.mustReturnBy.withHour(8);
    }

    @Override
    protected INotificationTaskStorage generateStorage(Lending lending) {
        return (NotificationTask task) -> {
            super.REPOSITORY.storeLendingAssociated(lending, task);
        };
    }
}
