package com.github.pieter_groenendijk.service.reservation.event.generator;

import com.github.pieter_groenendijk.model.Reservation;
import com.github.pieter_groenendijk.model.event.EventType;
import com.github.pieter_groenendijk.model.event.ReservationEvent;
import com.github.pieter_groenendijk.repository.event.IEventRepository;
import com.github.pieter_groenendijk.service.event.generator.DetachedEventGenerator;
import com.github.pieter_groenendijk.utils.scheduling.TaskStorage;

import java.time.LocalDateTime;

public abstract class DetachedReservationEventGenerator extends DetachedEventGenerator<Reservation, ReservationEvent> {
    protected DetachedReservationEventGenerator(IEventRepository repository, EventType type) {
        super(repository, type);
    }

    @Override
    protected abstract LocalDateTime determineScheduledDateTime(Reservation reservation);

    @Override
    protected ReservationEvent generateEmptyEvent() {
        return new ReservationEvent();
    }

    @Override
    protected TaskStorage<ReservationEvent> generateEventStorage() {
        // TODO: Hibernate determines the object type at runtime, so the need for generating event storage should be reconsidered.
        return super.REPOSITORY::store;
    }
}
