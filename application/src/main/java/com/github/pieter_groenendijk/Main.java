package com.github.pieter_groenendijk;

import com.github.pieter_groenendijk.hibernate.SessionFactoryFactory;
import com.github.pieter_groenendijk.services.notifications.NotificationService;
import com.github.pieter_groenendijk.storage.notifications.NotificationTaskRepository;
import org.hibernate.SessionFactory;

/**
 * Hello world!
 */
public class Main {
    public static void main(String[] args) {
        TaskScheduler taskScheduler = new TaskScheduler(1);
        SessionFactory sessionFactory = new SessionFactoryFactory().create();

        NotificationService notificationService = new NotificationService(
            taskScheduler,
            new NotificationTaskRepository(sessionFactory)
        );
    }
}
