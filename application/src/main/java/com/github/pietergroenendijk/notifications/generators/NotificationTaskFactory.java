package com.github.pietergroenendijk.notifications.generators;

import com.github.pietergroenendijk.notifications.NotificationTask;
import com.github.pietergroenendijk.notifications.UserContactDetails;
import com.github.pietergroenendijk.notifications.generators.finetreshold.FineThresholdNotificationTaskContext;
import com.github.pietergroenendijk.notifications.generators.finetreshold.FineTresholdNotificationTaskGenerator;
import com.github.pietergroenendijk.notifications.generators.returndate.ReturnDateNotificationTaskContext;
import com.github.pietergroenendijk.notifications.generators.returndate.ReturnDateNotificationTaskGenerator;
import com.github.pietergroenendijk.notifications.strategy.AlertNotificationStrategy;
import com.github.pietergroenendijk.notifications.strategy.NotificationStrategyFactory;
import com.github.pietergroenendijk.notifications.strategy.WarningNotificationStrategy;

public class NotificationTaskFactory {
    private final ReturnDateNotificationTaskGenerator RETURN_DATE_TASK_GENERATOR;
    private final FineTresholdNotificationTaskGenerator FINE_THRESHOLD_TASK_GENERATOR;

    public NotificationTaskFactory() {
        NotificationStrategyFactory strategyFactory = new NotificationStrategyFactory();
        AlertNotificationStrategy alertStrategy = strategyFactory.createAlertStrategy();
        WarningNotificationStrategy warningStrategy = strategyFactory.createWarningStrategy();

        this.RETURN_DATE_TASK_GENERATOR = new ReturnDateNotificationTaskGenerator(alertStrategy);
        this.FINE_THRESHOLD_TASK_GENERATOR = new FineTresholdNotificationTaskGenerator(warningStrategy);
    }

    public NotificationTask createReturnDateNotificationTask(UserContactDetails contactDetails, ReturnDateNotificationTaskContext context) {
        return RETURN_DATE_TASK_GENERATOR.generate(
            contactDetails,
            context
        );
    }

    public NotificationTask createFineTresholdNotificationTask(UserContactDetails contactDetails, FineThresholdNotificationTaskContext context) {
        return FINE_THRESHOLD_TASK_GENERATOR.generate(
            contactDetails,
            context
        );
    }
}
