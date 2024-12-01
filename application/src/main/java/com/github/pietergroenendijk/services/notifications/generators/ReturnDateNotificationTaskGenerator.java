package com.github.pietergroenendijk.services.notifications.generators;

import com.github.pietergroenendijk.Lending;
import com.github.pietergroenendijk.services.notifications.task.NotificationTask;
import com.github.pietergroenendijk.storage.notifications.NotificationTaskRepository;
import com.github.pietergroenendijk.storage.notifications.NotificationTaskStoreStrategy;
import com.github.pietergroenendijk.services.notifications.send_strategies.NotificationSendStrategy;

import java.time.LocalDateTime;

public class ReturnDateNotificationTaskGenerator extends NotificationTaskGenerator<Lending> {
    public ReturnDateNotificationTaskGenerator(NotificationSendStrategy sendStrategy, NotificationTaskRepository repository) {
        super(sendStrategy, repository);
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
        return lending.WILL_END_ON.withHour(8);
    }

    @Override
    protected NotificationTaskStoreStrategy generateStoreStrategy(Lending lending) {
        return (NotificationTask task) -> {
            super.REPOSITORY.storeLendingAssociated(lending, task);
        };
    }
}
