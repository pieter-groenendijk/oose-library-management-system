package com.github.pieter_groenendijk.services.send_strategies;

import com.github.pieter_groenendijk.model.Account;
import com.github.pieter_groenendijk.model.NotificationTask;
import com.github.pieter_groenendijk.services.notifications.notifiers.Notifier;
import com.github.pieter_groenendijk.services.notifications.send_strategies.NotificationSendStrategy;
import com.github.pieter_groenendijk.services.notifications.send_strategies.registry.SendStrategyType;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.HashMap;

public class TestNotificationSendStrategy {
    private final NotificationTask MOCK_NOTIFICATION_TASK = new NotificationTask(
        "title",
        "message",
        new Account(),
        LocalDateTime.now(),
        SendStrategyType.ALERT
    );

    static class MockNotifier implements Notifier {
        public int callCount = 0;
        public NotificationTask task;

        @Override
        public void send(NotificationTask task) {
            ++callCount;
            this.task = task;
        }
    }

    @Test
    public void testSend_zeroNotifiers() {
        MockNotifier[] notifiers = generateMockNotifiers(0);
        NotificationSendStrategy sendStrategy = new NotificationSendStrategy(notifiers);

        sendStrategy.send(MOCK_NOTIFICATION_TASK);

        assertEveryMockNotifierCalled(notifiers, 1);
    }

    @Test
    public void testSend_oneNotifier() {
        MockNotifier[] notifiers = generateMockNotifiers(1);
        NotificationSendStrategy sendStrategy = new NotificationSendStrategy(notifiers);

        sendStrategy.send(MOCK_NOTIFICATION_TASK);

        assertEveryMockNotifierCalled(notifiers, 1);
    }

    @Test
    public void testSend_multipleNotifiers() {
        MockNotifier[] notifiers = generateMockNotifiers(13);
        NotificationSendStrategy sendStrategy = new NotificationSendStrategy(notifiers);

        sendStrategy.send(MOCK_NOTIFICATION_TASK);

        assertEveryMockNotifierCalled(notifiers, 1);
    }

    @Test
    public void testSend_multipleNotifiersMultipleTimes() {
        MockNotifier[] notifiers = generateMockNotifiers(8);
        NotificationSendStrategy sendStrategy = new NotificationSendStrategy(notifiers);

        sendStrategy.send(MOCK_NOTIFICATION_TASK);

        assertEveryMockNotifierCalled(notifiers, 1);

        sendStrategy.send(MOCK_NOTIFICATION_TASK);

        assertEveryMockNotifierCalled(notifiers, 2);
    }

    @Test
    public void testSend_correctTaskPassedToNotifiers() {
        MockNotifier[] notifiers = generateMockNotifiers(5);
        NotificationSendStrategy sendStrategy = new NotificationSendStrategy(notifiers);

        sendStrategy.send(MOCK_NOTIFICATION_TASK);

        for (MockNotifier notifier: notifiers) {
            Assertions.assertEquals(MOCK_NOTIFICATION_TASK, notifier.task);
        }
    }

    private MockNotifier[] generateMockNotifiers(int length) {
        MockNotifier[] notifiers = new MockNotifier[length];

        for (int i = 0; i < notifiers.length; ++i) {
            notifiers[i] = new MockNotifier();
        }

        return notifiers;
    }

    private void assertEveryMockNotifierCalled(MockNotifier[] notifiers, int expectedCallAmount) {
        for(MockNotifier notifier: notifiers) {
            Assertions.assertEquals(expectedCallAmount, notifier.callCount);
        }
    }
}
