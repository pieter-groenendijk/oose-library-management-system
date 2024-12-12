package com.github.pieter_groenendijk.service.send_strategies.registry;

import com.github.pieter_groenendijk.service.notification.send_strategies.AlertNotificationSendStrategy;
import com.github.pieter_groenendijk.service.notification.send_strategies.NotificationSendStrategyFactory;
import com.github.pieter_groenendijk.service.notification.send_strategies.ReminderNotificationSendStrategy;
import com.github.pieter_groenendijk.service.notification.send_strategies.WarningNotificationSendStrategy;
import com.github.pieter_groenendijk.service.notification.send_strategies.registry.NotificationSendStrategyRegistry;
import com.github.pieter_groenendijk.service.notification.send_strategies.registry.SendStrategyType;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class TestNotificationSendStrategyRegistry {
    private NotificationSendStrategyRegistry sendStrategyRegistry;

    @BeforeEach
    public void beforeEach() {
        this.sendStrategyRegistry = new NotificationSendStrategyRegistry(
            new NotificationSendStrategyFactory()
        );
    }

    @Test
    public void testFromStrategyType_alert() {
        Assertions.assertInstanceOf(
            AlertNotificationSendStrategy.class,
            sendStrategyRegistry.fromStrategyType(SendStrategyType.ALERT)
        );
    }

    @Test
    public void testFromStrategyType_warning() {
        Assertions.assertInstanceOf(
            WarningNotificationSendStrategy.class,
            sendStrategyRegistry.fromStrategyType(SendStrategyType.WARNING)
        );
    }

    @Test
    public void testFromStrategyType_reminder() {
        Assertions.assertInstanceOf(
            ReminderNotificationSendStrategy.class,
            sendStrategyRegistry.fromStrategyType(SendStrategyType.REMINDER)
        );
    }
}
