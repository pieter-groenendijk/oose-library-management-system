package com.github.pietergroenendijk.notifications.generators;

import com.github.pietergroenendijk.AccountBase;
import com.github.pietergroenendijk.Lending;
import com.github.pietergroenendijk.notifications.NotificationTask;
import com.github.pietergroenendijk.notifications.NotificationTaskRepository;
import com.github.pietergroenendijk.notifications.generators.returndate.ReturnDateNotificationTaskGenerator;
import com.github.pietergroenendijk.notifications.strategy.AlertNotificationSendStrategy;
import com.github.pietergroenendijk.notifications.strategy.NotificationSendStrategyFactory;
import com.github.pietergroenendijk.notifications.strategy.WarningNotificationSendStrategy;

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

