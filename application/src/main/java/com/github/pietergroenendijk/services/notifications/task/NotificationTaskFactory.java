package com.github.pietergroenendijk.services.notifications.task;

import com.github.pietergroenendijk.AccountBase;
import com.github.pietergroenendijk.Lending;
import com.github.pietergroenendijk.storage.notifications.NotificationTaskRepository;
import com.github.pietergroenendijk.services.notifications.generators.ReturnDateNotificationTaskGenerator;
import com.github.pietergroenendijk.services.notifications.send_strategies.AlertNotificationSendStrategy;
import com.github.pietergroenendijk.services.notifications.send_strategies.NotificationSendStrategyFactory;
import com.github.pietergroenendijk.services.notifications.send_strategies.WarningNotificationSendStrategy;

public class NotificationTaskFactory {
    private final ReturnDateNotificationTaskGenerator RETURN_DATE_TASK_GENERATOR;

    public NotificationTaskFactory(NotificationTaskRepository repository) {
        NotificationSendStrategyFactory strategyFactory = new NotificationSendStrategyFactory();
        AlertNotificationSendStrategy alertStrategy = strategyFactory.createAlertStrategy();
        WarningNotificationSendStrategy warningStrategy = strategyFactory.createWarningStrategy();

        this.RETURN_DATE_TASK_GENERATOR = new ReturnDateNotificationTaskGenerator(alertStrategy, repository);
    }

    public NotificationTask createReturnDateNotificationTask(AccountBase account, Lending lending) {
        return RETURN_DATE_TASK_GENERATOR.generate(
            account,
            lending
        );
    }
}

