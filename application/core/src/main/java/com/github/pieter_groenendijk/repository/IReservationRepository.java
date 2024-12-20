package com.github.pieter_groenendijk.repository;

import com.github.pieter_groenendijk.model.Reservation;

import java.util.Optional;

public interface IReservationRepository {

    Optional<Reservation> retrieveReservationById(long reservationId);
    Optional<Reservation> retrieveReservationByMembershipId(long membershipId);

    void store(Reservation reservation);
    void updateReservation(Reservation reservation);
    void deleteReservationById(long reservationId);

}
