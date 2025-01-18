package com.github.pieter_groenendijk.repository.notification;

import com.github.pieter_groenendijk.model.event.Event;
import com.github.pieter_groenendijk.model.notification.Notification;
import com.github.pieter_groenendijk.repository.scheduling.TaskRepository;
import org.hibernate.SessionFactory;

import java.time.LocalDateTime;
import java.util.List;

public class NotificationRepository extends TaskRepository<Notification> {
    public NotificationRepository(SessionFactory sessionFactory) {
        super(sessionFactory);
    }

    // TODO: We could probably generalize this somehow
    @Override
    public List<Notification> retrieveUntil(LocalDateTime until) throws Exception {
        return super.performAtomicOperationReturning((session -> {
            return session.createQuery(
                    "select n from Notification as n where scheduledAt <= :until",
                    Notification.class
                )
                    .setParameter("until", until)
                    .getResultList();
        }));
    }
}
