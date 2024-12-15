package com.github.pieter_groenendijk.service;

import com.github.pieter_groenendijk.model.Loan;
import com.github.pieter_groenendijk.model.Reservation;

import java.util.List;

public interface IReservationService {
    Reservation createReservation(Reservation reservation);
    Reservation getReservationById(int id);
    Reservation updateReservation(Reservation reservation);
    void deleteReservation(int reservationId);
    boolean readyForPickup(int reservationId);
    List<Reservation> getAllReservations();
    List<Reservation> getReservationsByMembershipId(int membershipId);

    void handleExpiredReservations();
    void logUncollectedReservations();

    Loan convertReservationToLoan(long reservationId);


}
