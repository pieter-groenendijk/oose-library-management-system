package com.github.pieter_groenendijk.service;
import com.github.pieter_groenendijk.model.Reservation;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

import static com.github.pieter_groenendijk.service.ServiceUtils.PICKUP_DAYS;


public interface IReservationService {
    Reservation store(long membershipId, long copyId);
    Reservation getReservationById(long reservationId);
    Reservation updateReservation(Reservation reservation);
    void cancelReservation(long reservationId);


    boolean readyForPickup(long reservationId);


    Date generateReservationPickUpDate();

    void handleUncollectedReservations(long membershipId, Date currentDate);


    void removeReservation(long reservationId);

    Date getPickupDate(long reservationId);
}
