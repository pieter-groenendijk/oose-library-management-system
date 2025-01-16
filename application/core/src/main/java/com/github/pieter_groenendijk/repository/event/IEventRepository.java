package com.github.pieter_groenendijk.repository.event;

import com.github.pieter_groenendijk.model.event.Event;
import com.github.pieter_groenendijk.model.event.LoanEvent;
import com.github.pieter_groenendijk.model.event.ReservationEvent;

import java.time.LocalDateTime;
import java.util.List;

public interface IEventRepository {
    void storeLoanEvent(LoanEvent event);
    void storeReservationEvent(ReservationEvent event);

    List<Event<?>> retrieveUntil(LocalDateTime scheduledAt); // TODO: Could generalize this in a separate interface; very similar for notifications.
}
