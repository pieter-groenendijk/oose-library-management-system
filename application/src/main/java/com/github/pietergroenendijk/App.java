package com.github.pietergroenendijk;

import com.github.pietergroenendijk.notifications.NotificationService;

/**
 * Hello world!
 */
public class App {
    public static void main(String[] args) {
        TaskScheduler taskScheduler = new TaskScheduler(1);

        new NotificationService(taskScheduler);
    }
}
