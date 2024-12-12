package com.github.pieter_groenendijk.repository;

import com.github.pieter_groenendijk.model.Reservation;

import java.util.Optional;

public class ReservationRepository implements IReservationRepository {

    @Override
    public Optional<Reservation> retrieveReservationById(long reservationId) {
        return Optional.empty();
    }

    @Override
    public Reservation store(Reservation reservation) {
        return null;
    }

    @Override
    public Reservation updateReservation(Reservation reservation) {
        return null;
    }

    @Override
    public Reservation deleteReservationById(long reservationId) {
        return null;
    }
}
