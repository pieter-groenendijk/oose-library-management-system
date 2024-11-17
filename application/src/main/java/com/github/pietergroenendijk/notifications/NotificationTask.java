package com.github.pietergroenendijk.notifications;

import com.github.pietergroenendijk.notifications.strategy.NotificationStrategy;

public record NotificationTask<T>(
        T context,
        Notification notification,
        NotificationStrategy strategy,
        java.time.LocalDateTime scheduledDateTime,
        UserContactDetails contactDetails
){

}
