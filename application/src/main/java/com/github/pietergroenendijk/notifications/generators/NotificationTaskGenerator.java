package com.github.pietergroenendijk.notifications.generators;

import com.github.pietergroenendijk.notifications.*;
import com.github.pietergroenendijk.notifications.strategy.NotificationStrategy;

import java.time.LocalDateTime;

public abstract class NotificationTaskGenerator<T> {
    protected final NotificationTaskRepository REPOSITORY;

    private final NotificationStrategy STRATEGY;

    protected NotificationTaskGenerator(NotificationStrategy strategy, NotificationTaskRepository repository) {
        this.STRATEGY = strategy;
        this.REPOSITORY = repository;
    }

    public final NotificationTask generate(UserContactDetails contactDetails, T context) {
        Notification notification = new Notification(
            generateTitle(context),
            generateMessage(context)
        );

        return new NotificationTask(
            notification,
            this.STRATEGY,
            this.generateStoreStrategy(context),
            determineSendDateTime(context),
            contactDetails
        );
    }

    protected abstract String generateTitle(T context);

    protected abstract String generateMessage(T context);

    protected abstract LocalDateTime determineSendDateTime(T context);

    protected abstract NotificationTaskStoreStrategy generateStoreStrategy(T context);
}
