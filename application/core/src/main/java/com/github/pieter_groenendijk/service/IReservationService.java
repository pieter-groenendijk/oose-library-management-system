package com.github.pieter_groenendijk.service;
import com.github.pieter_groenendijk.model.Reservation;

import java.util.Date;


public interface IReservationService {
    Reservation store(long membershipId, long copyId);
    Reservation getReservationById(long reservationId);
    Reservation updateReservation(Reservation reservation);
    void cancelReservation(long reservationId);


    boolean readyForPickup(long reservationId);
    Date generateReservationPickUpDate(long reservationId);



    void handleUncollectedReservations(long membershipId, Date currentDate);

    void logUncollectedReservations(long membershipId, Date currentDate);


    void removeReservation(long reservationId);

    Date getPickupDate(long reservationId);
}
