package com.github.pieter_groenendijk.service;

import com.github.pieter_groenendijk.model.Loan;
import com.github.pieter_groenendijk.model.Reservation;

import java.util.List;

public interface IReservationService {
    Reservation createReservation(Reservation reservation);
    Reservation getReservationById(long ReservationId);
    Reservation updateReservation(Reservation reservation);
    void deleteReservation(long reservationId);
    boolean readyForPickup(long reservationId);
    List<Reservation> getAllReservations();
    List<Reservation> getReservationsByMembershipId(long membershipId);

    void handleExpiredReservations();
    void logUncollectedReservations();

    Loan convertReservationToLoan(long reservationId);


}
