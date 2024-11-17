package com.github.pietergroenendijk.notifications.generators;

import com.github.pietergroenendijk.notifications.Notification;
import com.github.pietergroenendijk.notifications.NotificationTask;
import com.github.pietergroenendijk.notifications.UserContactDetails;
import com.github.pietergroenendijk.notifications.strategy.NotificationStrategy;

import java.time.LocalDateTime;

public abstract class NotificationTaskGenerator<T extends NotificationTaskContext> {
    private final NotificationStrategy STRATEGY;

    protected NotificationTaskGenerator(NotificationStrategy strategy) {
        this.STRATEGY = strategy;
    }

    public final NotificationTask generate(UserContactDetails contactDetails, T context) {
        Notification notification = new Notification(
            generateTitle(context),
            generateMessage(context)
        );

        return new NotificationTask(
            context.REFERENCE_ID,
            notification,
            this.STRATEGY,
            determineSendDateTime(context),
            contactDetails
        );
    }

    protected abstract String generateTitle(T data);

    protected abstract String generateMessage(T data);

    protected abstract LocalDateTime determineSendDateTime(T data);
}
