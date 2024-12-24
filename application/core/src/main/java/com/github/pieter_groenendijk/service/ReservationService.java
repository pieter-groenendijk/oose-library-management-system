package com.github.pieter_groenendijk.service;

import com.github.pieter_groenendijk.exception.EntityNotFoundException;
import com.github.pieter_groenendijk.model.Account;
import com.github.pieter_groenendijk.model.Membership;
import com.github.pieter_groenendijk.model.Reservation;
import com.github.pieter_groenendijk.repository.IAccountRepository;
import com.github.pieter_groenendijk.repository.IMembershipRepository;
import com.github.pieter_groenendijk.repository.IReservationRepository;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;

import static com.github.pieter_groenendijk.service.ServiceUtils.PICKUP_DAYS;
import static com.github.pieter_groenendijk.service.ServiceUtils.PICKUP_EXPIRY_DAYS;

public class ReservationService implements IReservationService {
    private final IAccountRepository accountRepository;
    private final IReservationRepository reservationRepository;
    private Date pickupDate;
    IMembershipRepository membershipRepository;


    public ReservationService(IReservationRepository reservationRepository, IMembershipRepository membershipRepository, IAccountRepository accountRepository) {
        this.reservationRepository = reservationRepository;
        this.membershipRepository = membershipRepository;
        this.accountRepository = accountRepository;
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
    public void cancelReservation(long reservationId) {

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
    public Date generateReservationPickUpDate() {
        LocalDate localDate = LocalDate.now().plusDays(PICKUP_DAYS);
        return Date.from(localDate.atStartOfDay(ZoneId.systemDefault()).toInstant());
    }

    @Override
    public void handleUncollectedReservations(long membershipId, Date currentDate) {
        Membership membership = membershipRepository.retrieveMembershipById(membershipId)
                .orElseThrow(() -> new EntityNotFoundException("Membership with ID " + membershipId + " not found."));

        Account account = membership.getAccount();

        List<Reservation> reservations = reservationRepository.retrieveReservationsByMembershipId(membershipId);

        reservations.stream()
                .filter(reservation -> !reservation.isCollected() && reservation.getReservationPickUpDate().before(currentDate))
                .forEach(reservation -> {
                    reservation.setExpired(true);


                    account.incrementUncollectedReservationCount();


                    reservationRepository.updateReservation(reservation);
                });


        accountRepository.store(account);
    }

    @Override
    public Date getPickupDate(long reservationId) {
        Date pickupDate = generateReservationPickUpDate();
        return pickupDate;
    }
}
