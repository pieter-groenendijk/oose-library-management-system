package com.github.pieter_groenendijk.repository.event;

import com.github.pieter_groenendijk.model.event.Event;
import com.github.pieter_groenendijk.model.event.LoanEvent;
import com.github.pieter_groenendijk.model.event.ReservationEvent;
import com.github.pieter_groenendijk.repository.scheduling.TaskRepository;
import org.hibernate.SessionFactory;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class EventRepository extends TaskRepository<Event<?>> implements IEventRepository {
    public EventRepository(SessionFactory sessionFactory) {
        super(sessionFactory);
    }

    @Override
    public void storeLoanEvent(LoanEvent event) {

    }

    @Override
    public void storeReservationEvent(ReservationEvent event) {

    }

    // TODO: We could probably generalize this somehow
    // TODO: We should probably remove the generics for the association so this is not pain.
    @Override
    public List<Event<?>> retrieveUntil(LocalDateTime until) throws Exception {
        return List.of();
//        return super.performAtomicOperationReturning((session -> {
//            List<Event> rawEvents = session.createQuery(
//                    "select n from Event as n where scheduledAt <= :until",
//                    Event.class
//                )
//                .setParameter("until", until)
//                .getResultList();
//
//            return new ArrayList<>((Collection<? extends Event<?>>) rawEvents);
//        }));
    }
}
