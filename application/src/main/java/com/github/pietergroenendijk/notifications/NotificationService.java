package com.github.pietergroenendijk.notifications;

import com.github.pietergroenendijk.TaskScheduler;
import com.github.pietergroenendijk.notifications.generators.NotificationTaskFactory;
import com.github.pietergroenendijk.notifications.generators.returndate.ReturnDateNotificationTaskContext;

public class NotificationService {
    private final NotificationTaskFactory TASK_FACTORY = new NotificationTaskFactory();
    private final NotificationTaskScheduler SCHEDULER;

    public NotificationService(TaskScheduler scheduler) {
        this.SCHEDULER = new NotificationTaskScheduler(scheduler);
    }

    public void scheduleNewLendingBundle(UserContactDetails contactDetails, ReturnDateNotificationTaskContext context) {
        this.scheduleReturnDateNotification(contactDetails, context);
    }

    private void scheduleReturnDateNotification(UserContactDetails contactDetails, ReturnDateNotificationTaskContext context) {
        this.SCHEDULER.schedule(
            this.TASK_FACTORY.createReturnDateNotificationTask(contactDetails, context)
        );
    }
}
