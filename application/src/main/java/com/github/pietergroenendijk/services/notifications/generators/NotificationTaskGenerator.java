package com.github.pietergroenendijk.services.notifications.generators;

import com.github.pietergroenendijk.AccountBase;
import com.github.pietergroenendijk.services.notifications.task.Notification;
import com.github.pietergroenendijk.services.notifications.send_strategies.NotificationSendStrategy;
import com.github.pietergroenendijk.services.notifications.task.NotificationTask;
import com.github.pietergroenendijk.storage.notifications.NotificationTaskRepository;
import com.github.pietergroenendijk.storage.notifications.NotificationTaskStoreStrategy;

import java.time.LocalDateTime;

public abstract class NotificationTaskGenerator<T> {
    protected final NotificationTaskRepository REPOSITORY;

    private final NotificationSendStrategy SEND_STRATEGY;

    protected NotificationTaskGenerator(NotificationSendStrategy sendStrategy, NotificationTaskRepository repository) {
        this.SEND_STRATEGY = sendStrategy;
        this.REPOSITORY = repository;
    }

    public final NotificationTask generate(AccountBase account, T context) {
        Notification notification = new Notification(
            generateTitle(context),
            generateMessage(context)
        );

        return new NotificationTask(
            notification,
            this.SEND_STRATEGY,
            this.generateStoreStrategy(context),
            determineScheduleDateTime(context),
            account
        );
    }

    protected abstract String generateTitle(T context);

    protected abstract String generateMessage(T context);

    protected abstract LocalDateTime determineScheduleDateTime(T context);

    protected abstract NotificationTaskStoreStrategy generateStoreStrategy(T context);
}
