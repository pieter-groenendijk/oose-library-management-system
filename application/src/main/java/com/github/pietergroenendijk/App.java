package com.github.pietergroenendijk;

import com.github.pietergroenendijk.notifications.NotificationService;

public class App {
    public static void main(String[] args) {
        TaskScheduler taskScheduler = new TaskScheduler(1);

        new NotificationService(taskScheduler);
    }
}
