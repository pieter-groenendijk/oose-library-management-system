package com.github.pieter_groenendijk.service;

import com.github.pieter_groenendijk.model.Reservation;
import com.github.pieter_groenendijk.repository.ReservationRepository;

import java.util.Date;

public class ReservationService implements IReservationService {

    public ReservationService(ReservationRepository reservationRepository) {

    }

    @Override
    public Reservation store(long membershipId, long copyId) {
        return null;
    }

    @Override
    public Reservation getReservationById(long reservationId) {
        return null;
    }

    @Override
    public Reservation updateReservation(Reservation reservation) {
        return null;
    }

    @Override
    public void cancelReservation(long reservationId) {

    }

    @Override
    public boolean readyForPickup(long reservationId) {
        return false;
    }

    @Override
    public Date generateReservationPickUpDate(long reservationId) {
        return null;
    }

    @Override
    public void handleUncollectedReservations(long membershipId, Date currentDate) {

    }

    @Override
    public void logUncollectedReservations(long membershipId, Date currentDate) {

    }

    @Override
    public void removeReservation(long reservationId) {

    }

    @Override
    public Date getPickupDate(long reservationId) {
        return null;
    }
}
