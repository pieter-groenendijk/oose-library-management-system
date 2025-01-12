package com.github.pieter_groenendijk.service.reservation;
import com.github.pieter_groenendijk.model.Reservation;

import java.util.Date;


public interface IReservationService {
    Reservation store(Reservation reservation);
    Reservation getReservationById(long reservationId);
    Reservation updateReservation(Reservation reservation);
    void cancelReservation(long reservationId);


    boolean readyForPickup(long reservationId);


    Date generateReservationPickUpDate();

    void handleUncollectedReservations(long membershipId, Date currentDate) throws Exception;

    Date getPickupDate(long reservationId);
}
