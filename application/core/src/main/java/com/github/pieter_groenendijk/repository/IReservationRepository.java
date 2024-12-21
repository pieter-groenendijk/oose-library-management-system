package com.github.pieter_groenendijk.repository;

import com.github.pieter_groenendijk.model.Reservation;

import java.util.List;
import java.util.Optional;

public interface IReservationRepository {

    Optional<Reservation> retrieveReservationById(long reservationId);
    List<Reservation> retrieveReservationByMembershipId(long membershipId);

    void store(Reservation reservation);
    void updateReservation(Reservation reservation);
    void deleteReservationById(long reservationId);

}
