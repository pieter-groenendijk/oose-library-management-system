package com.github.pieter_groenendijk.service;

import com.github.pieter_groenendijk.exception.EntityNotFoundException;
import com.github.pieter_groenendijk.model.Reservation;
import com.github.pieter_groenendijk.repository.IMembershipRepository;
import com.github.pieter_groenendijk.repository.IReservationRepository;
import com.github.pieter_groenendijk.repository.ReservationRepository;


import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

public class ReservationService implements IReservationService {
    private static final long PICKUP_DAYS = 7;
    private static final long PICKUP_EXPIRY_DAYS = 14;
    private IReservationRepository reservationRepository;
    private IMembershipRepository membershipRepository;

    public ReservationService(IReservationRepository reservationRepository) {
        this.reservationRepository = reservationRepository;
        this.membershipRepository = membershipRepository;
    }

    @Override
    public Reservation store(long membershipId, long copyId) {
        return null;
    }

    @Override
    public Reservation getReservationById(long reservationId) {
        return reservationRepository.retrieveReservationById(reservationId)
                .orElseThrow(() -> new EntityNotFoundException("Membership with ID " + reservationId + " not found."));
    }

    @Override
    public Reservation updateReservation(Reservation reservation) {
        return null;
    }

    
    @Override
    public boolean readyForPickup(long reservationId) {
        return reservationRepository.retrieveReservationById(reservationId)
                .map(reservation -> {
                    LocalDate reservationDate = toLocalDate(reservation.getReservationDate());
                    LocalDate pickUpStartDate = reservationDate.plusDays(PICKUP_DAYS);
                    LocalDate pickUpEndDate = reservationDate.plusDays(PICKUP_EXPIRY_DAYS);
                    LocalDate today = LocalDate.now();

                    System.out.println("pickUpStartDate: " + pickUpStartDate);
                    System.out.println("pickUpEndDate: " + pickUpEndDate);
                    System.out.println("today: " + today);
                    return !today.isBefore(pickUpStartDate) && !today.isAfter(pickUpEndDate);
                })
                .orElse(false);
    }

    private LocalDate toLocalDate(Date date) {
        return date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
    }

    @Override
    public Date generateReservationPickUpDate(long reservationId) {
        LocalDate localDate = LocalDate.now().plusDays(PICKUP_DAYS);
        return java.util.Date.from(localDate.atStartOfDay(ZoneId.systemDefault()).toInstant());
    }

    @Override
    public void handleUncollectedReservations(long membershipId, Date currentDate) {

    }

    @Override
    public void removeReservation(long reservationId) {

    }
}
