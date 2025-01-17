package com.github.pieter_groenendijk.repository;

import com.github.pieter_groenendijk.model.DTO.ReservationDTO;
import com.github.pieter_groenendijk.model.Reservation;

import java.util.List;
import java.util.Optional;

public interface IReservationRepository {

    Optional<Reservation> retrieveReservationById(long reservationId);

    Reservation store(Reservation reservation);
    Reservation updateReservation(Reservation reservation);

    List<Reservation> retrieveReservationByMembershipId(long membershipId);

}
