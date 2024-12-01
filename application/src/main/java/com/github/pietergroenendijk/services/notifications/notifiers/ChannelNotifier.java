package com.github.pietergroenendijk.services.notifications.notifiers;

import com.github.pietergroenendijk.entities.NotificationTask;

public abstract class ChannelNotifier implements Notifier {
    private final byte MAXIMUM_AMOUNT_OF_ATTEMPTS;

    public ChannelNotifier(byte maximumAmountOfAttempts) {
        this.MAXIMUM_AMOUNT_OF_ATTEMPTS = maximumAmountOfAttempts;
    }

    public ChannelNotifier() {
        this.MAXIMUM_AMOUNT_OF_ATTEMPTS = 3;
    }

    @Override
    public void send(NotificationTask task) {
        this.send(task, (byte)1);
    }

    private void send(NotificationTask task, byte amountOfAttempts) {
        try {
            this.attempt(task);
        } catch (Exception e) {
            this.handleSendingError(task, amountOfAttempts);
        }
    }

    protected abstract void attempt(NotificationTask task) throws Exception;

    private void handleSendingError(NotificationTask task, byte amountOfAttempts) {
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
}
