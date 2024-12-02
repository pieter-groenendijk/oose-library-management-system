package com.github.pieter_groenendijk;

import com.github.pieter_groenendijk.services.notifications.NotificationService;

/**
 * Hello world!
 */
public class Main {
    public static void main(String[] args) {
        TaskScheduler taskScheduler = new TaskScheduler(1);

        new NotificationService(taskScheduler);


        HibernateTest.main(args);
    }
}
