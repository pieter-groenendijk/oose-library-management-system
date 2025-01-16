package com.github.pieter_groenendijk.service.notification.notifiers;

import com.github.pieter_groenendijk.model.notification.Notification;

public abstract class ChannelNotifier implements Notifier {
    private final byte MAXIMUM_AMOUNT_OF_ATTEMPTS;

    public ChannelNotifier(byte maximumAmountOfAttempts) {
        this.MAXIMUM_AMOUNT_OF_ATTEMPTS = maximumAmountOfAttempts;
    }

    public ChannelNotifier() {
        this.MAXIMUM_AMOUNT_OF_ATTEMPTS = 3;
    }

    @Override
    public void send(Notification task) {
        this.send(task, (byte)1);
    }

    private void send(Notification task, byte amountOfAttempts) {
        try {
            this.attempt(task);
        } catch (Exception e) {
            this.handleSendingError(task, amountOfAttempts);
        }
    }

    protected abstract void attempt(Notification task) throws Exception;

    private void handleSendingError(Notification task, byte amountOfAttempts) {
        if (amountOfAttempts < this.MAXIMUM_AMOUNT_OF_ATTEMPTS) {
            ++amountOfAttempts;
            this.send(task, amountOfAttempts);
        } else {
            logFailedSending(task);
        }
    }

    private void logFailedSending(Notification task) {
        System.out.println("Failed to send notification: " + task.toString());
    }
}
