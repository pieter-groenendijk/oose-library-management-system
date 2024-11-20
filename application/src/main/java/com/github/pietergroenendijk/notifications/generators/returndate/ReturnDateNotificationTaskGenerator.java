package com.github.pietergroenendijk.notifications.generators.returndate;

import com.github.pietergroenendijk.Lending;
import com.github.pietergroenendijk.notifications.NotificationTask;
import com.github.pietergroenendijk.notifications.NotificationTaskRepository;
import com.github.pietergroenendijk.notifications.NotificationTaskStoreStrategy;
import com.github.pietergroenendijk.notifications.generators.NotificationTaskGenerator;
import com.github.pietergroenendijk.notifications.strategy.NotificationSendStrategy;

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
