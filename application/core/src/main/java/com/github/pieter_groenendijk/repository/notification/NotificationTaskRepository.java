package com.github.pieter_groenendijk.repository.notification;

import com.github.pieter_groenendijk.model.notification.Notification;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class NotificationTaskRepository implements INotificationTaskRepository {
    private final SessionFactory SESSION_FACTORY;

    public NotificationTaskRepository(SessionFactory sessionFactory) {
        this.SESSION_FACTORY = sessionFactory;
    }

    // TODO: Do something with a status so that it will not retrieve already previously retrieved items.
    @Override
    public List<Notification> retrieve(LocalDateTime scheduledUntil) {
        List<Notification> tasks;
        try (Session session = this.SESSION_FACTORY.openSession()) {
            Transaction transaction = session.beginTransaction();

            try {
                tasks = session.createQuery(
                        "select nt from Notification as nt where scheduledAt <= :scheduledUntil",
                        Notification.class
                    )
                    .setParameter("scheduledUntil", scheduledUntil)
                    .list();

                transaction.commit();
            } catch(Exception exception) {
                transaction.rollback();

                tasks = new ArrayList<>(); // To prevent returning null
            }
        }

        return tasks;
    }
}
