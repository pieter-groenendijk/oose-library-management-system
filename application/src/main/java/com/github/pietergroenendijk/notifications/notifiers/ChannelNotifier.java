package com.github.pietergroenendijk.notifications.notifiers;

import com.github.pietergroenendijk.notifications.NotificationTask;

public abstract class ChannelNotifier implements Notifier {
    private final int MAXIMUM_AMOUNT_OF_ATTEMPTS;

    public ChannelNotifier(int maximumAmountOfAttempts) {
        this.MAXIMUM_AMOUNT_OF_ATTEMPTS = maximumAmountOfAttempts;
    }

    public ChannelNotifier() {
        this.MAXIMUM_AMOUNT_OF_ATTEMPTS = 3;
    }

    @Override
    public void send(NotificationTask task) {
        this.send(task, 1);
    }

    private void send(NotificationTask task, int amountOfAttempts) {
        try {
            this.attempt(task);
        } catch (Exception e) {
            this.handleSendingError(task, amountOfAttempts);
        }
    }

    private void handleSendingError(NotificationTask task, int amountOfAttempts) {
        if (amountOfAttempts < this.MAXIMUM_AMOUNT_OF_ATTEMPTS) {
            ++amountOfAttempts;
            this.send(task, amountOfAttempts);
        } else {
            logFailedSending(task);
        }
    }

    private void logFailedSending(NotificationTask task) {
        System.out.println("Failed to send notification: " + task.toString());
    }

    protected abstract void attempt(NotificationTask task) throws Exception;
}
