package com.github.pietergroenendijk.services.notifications;

import com.github.pietergroenendijk.AccountBase;
import com.github.pietergroenendijk.Lending;
import com.github.pietergroenendijk.TaskScheduler;
import com.github.pietergroenendijk.storage.notifications.NotificationTaskRepository;
import com.github.pietergroenendijk.services.notifications.scheduling.NotificationTaskScheduler;
import com.github.pietergroenendijk.services.notifications.task.NotificationTaskFactory;

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
