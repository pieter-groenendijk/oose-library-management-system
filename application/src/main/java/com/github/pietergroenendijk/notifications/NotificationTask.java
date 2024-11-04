package com.github.pietergroenendijk.notifications;

import java.time.LocalTime;

public record NotificationTask(
    Notification notification,
    NotificationStrategy strategy,
    LocalTime scheduledDateTime,
    UserContactDetails contactDetails
){

}
