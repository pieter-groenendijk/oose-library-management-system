package com.github.pieter_groenendijk.service;
import com.github.pieter_groenendijk.model.Reservation;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

import static com.github.pieter_groenendijk.service.ServiceUtils.PICKUP_DAYS;


public interface IReservationService {
    Reservation store(Reservation reservation);
    Reservation getReservationById(long reservationId);
    Reservation updateReservation(Reservation reservation);
    void cancelReservation(long reservationId);


    boolean readyForPickup(long reservationId);


    Date generateReservationPickUpDate();

    void handleUncollectedReservations(long membershipId, Date currentDate);

    Date getPickupDate(long reservationId);
}
