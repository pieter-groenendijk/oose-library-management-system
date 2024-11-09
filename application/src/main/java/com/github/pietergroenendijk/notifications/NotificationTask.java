package com.github.pietergroenendijk.notifications;

import com.github.pietergroenendijk.notifications.strategy.NotificationStrategy;

import java.time.LocalTime;

public record NotificationTask(
    Notification notification,
    NotificationStrategy strategy,
    LocalTime scheduledDateTime,
    UserContactDetails contactDetails
){

}
