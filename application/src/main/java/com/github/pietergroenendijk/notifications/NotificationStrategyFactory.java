package com.github.pietergroenendijk.notifications;

public class NotificationStrategyFactory {
    private final EmailNotifier emailNotifier = new EmailNotifier();
    private final SMSNotifier smsNotifier = new SMSNotifier();
    private final AppNotifier appNotifier = new AppNotifier();

    public NotificationStrategy createAlertStrategy() {
        return new NotificationStrategy(new Notifier[]{
            emailNotifier,
            smsNotifier,
            appNotifier
        });
    }

    public NotificationStrategy createWarningStrategy() {
        return new NotificationStrategy(new Notifier[]{
            appNotifier,
        });
    }

    public NotificationStrategy createReminderStrategy() {
        return new NotificationStrategy(new Notifier[]{});
    }
}
