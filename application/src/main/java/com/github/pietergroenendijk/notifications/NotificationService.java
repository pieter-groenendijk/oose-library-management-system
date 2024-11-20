package com.github.pietergroenendijk.notifications;

import com.github.pietergroenendijk.AccountBase;
import com.github.pietergroenendijk.Lending;
import com.github.pietergroenendijk.TaskScheduler;
import com.github.pietergroenendijk.notifications.generators.NotificationTaskFactory;

public class NotificationService {
    private final NotificationTaskFactory TASK_FACTORY;
    private final NotificationTaskScheduler SCHEDULER;

    public NotificationService(TaskScheduler scheduler) {
        NotificationTaskRepository repository = new NotificationTaskRepository();

        this.TASK_FACTORY = new NotificationTaskFactory(repository);
        this.SCHEDULER = new NotificationTaskScheduler(scheduler, repository);
    }

    public void scheduleNewLendingBundle(AccountBase account, Lending lending) {
        this.scheduleReturnDateNotification(account, lending);
    }

    private void scheduleReturnDateNotification(AccountBase account, Lending lending) {
        this.SCHEDULER.schedule(
            this.TASK_FACTORY.createReturnDateNotificationTask(account, lending)
        );
    }
}
