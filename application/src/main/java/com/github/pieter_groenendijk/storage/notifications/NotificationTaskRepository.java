package com.github.pieter_groenendijk.storage.notifications;

import com.github.pieter_groenendijk.model.Lending;
import com.github.pieter_groenendijk.model.LendingAssociatedNotificationTask;
import com.github.pieter_groenendijk.model.NotificationTask;
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
    public List<NotificationTask> retrieve(LocalDateTime scheduledUntil) {
        List<NotificationTask> tasks;
        try (Session session = this.SESSION_FACTORY.openSession()) {
            Transaction transaction = session.beginTransaction();

            try {
                tasks = session.createQuery(
                        "select nt from NotificationTask as nt where scheduledAt <= :scheduledUntil",
                        NotificationTask.class
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

    /**
     * Stores the task AND the association with a lending
     * NOTE: It expects the lending to be already persisted.
     */
    public void storeLendingAssociated(Lending lending, NotificationTask task) {
        // TODO insert using a view, so that there only one statement is sent, as to minimize latency.

        try (Session session = this.SESSION_FACTORY.openSession()) {
            Transaction transaction = session.beginTransaction();

            try {
                session.persist(task);
                session.persist(new LendingAssociatedNotificationTask(
                    lending,
                    task
                ));

                transaction.commit();
            } catch(Exception exception) {
                transaction.rollback();
            }
        }
    }

    public void markCompleted(NotificationTask task) {
        // TODO mark completed in database
    }
}
