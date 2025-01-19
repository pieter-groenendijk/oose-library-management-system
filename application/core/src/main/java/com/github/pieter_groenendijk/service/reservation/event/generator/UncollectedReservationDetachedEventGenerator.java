package com.github.pieter_groenendijk.service.reservation.event.generator;

import com.github.pieter_groenendijk.model.Reservation;
import com.github.pieter_groenendijk.model.event.EventType;
import com.github.pieter_groenendijk.repository.event.IEventRepository;

import java.time.LocalDateTime;

public class UncollectedReservationDetachedEventGenerator extends DetachedReservationEventGenerator {
    protected UncollectedReservationDetachedEventGenerator(IEventRepository repository) {
        super(
            repository,
            EventType.UNCOLLECTED_RESERVATION_EVENT
        );
    }

    @Override
    protected LocalDateTime determineScheduledDateTime(Reservation reservation) {
        return LocalDateTime.now();
//        return TimeUtils.dateToLocalDateTime(reservation.getReservationPickUpDate())
//            .plusHours(1); // 1 hour leeway
    }
}
