package com.github.pietergroenendijk.notifications;

import java.time.LocalTime;

abstract class NotificationTaskGenerator<T> {
    private final NotificationStrategy STRATEGY;

    protected NotificationTaskGenerator(NotificationStrategy strategy) {
        this.STRATEGY = strategy;
    }

    public final NotificationTask generate(UserContactDetails contactDetails, T data) {
        Notification notification = new Notification(
            generateTitle(data),
            generateMessage(data)
        );

        return new NotificationTask(
            notification,
            this.STRATEGY,
            determineSendDateTime(data),
            contactDetails
        );
    }

    protected abstract String generateTitle(T data);

    protected abstract String generateMessage(T data);

    protected abstract LocalTime determineSendDateTime(T data);
}
