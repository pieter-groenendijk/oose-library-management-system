package com.github.pieter_groenendijk.service.event.generator;

import com.github.pieter_groenendijk.model.Reservation;
import com.github.pieter_groenendijk.model.event.EventType;
import com.github.pieter_groenendijk.model.event.ReservationEvent;
import com.github.pieter_groenendijk.repository.event.IEventRepository;
import com.github.pieter_groenendijk.utils.scheduling.TaskStorage;
import com.github.pieter_groenendijk.utils.time.TimeUtils;

import java.time.LocalDateTime;

public class UncollectedReservationDetachedEventGenerator extends DetachedEventGenerator<Reservation, ReservationEvent>{
    protected UncollectedReservationDetachedEventGenerator(IEventRepository repository) {
        super(
            repository,
            EventType.UNCOLLECTED_RESERVATION_EVENT
        );
    }

    @Override
    protected ReservationEvent generateEmptyEvent() {
        return new ReservationEvent();
    }

    @Override
    protected LocalDateTime determineScheduledDateTime(Reservation reservation) {
        return TimeUtils.dateToLocalDateTime(reservation.getReservationPickUpDate())
            .plusHours(1); // 1 hour leeway
    }

    @Override
    protected TaskStorage<ReservationEvent> generateEventStorage() {
        return this.REPOSITORY::storeReservationEvent;
    }
}
