package com.github.pieter_groenendijk.repository;

import com.github.pieter_groenendijk.model.Reservation;

import java.util.Optional;

public interface IReservationRepository {

    Optional<Reservation> retrieveReservationById(long reservationId);
    Optional<Reservation> retrieveReservationByMembershipId(long membershipId);

    Reservation store(Reservation reservation);
    String updateReservation(Reservation reservation);
    Reservation deleteReservationById(long reservationId);

}
