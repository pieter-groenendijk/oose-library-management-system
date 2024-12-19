package com.github.pieter_groenendijk.service;
import com.github.pieter_groenendijk.model.Reservation;

import java.util.Date;


public interface IReservationService {
    Reservation store(long membershipId, long copyId);
    Reservation getReservationById(long reservationId);
    Reservation updateReservation(Reservation reservation);

    boolean readyForPickup(long reservationId);
    Date generateReservationPickUpDate(long reservationId);

    void handleUncollectedReservations(long membershipId, Date currentDate);

    void removeReservation(long reservationId);

}
