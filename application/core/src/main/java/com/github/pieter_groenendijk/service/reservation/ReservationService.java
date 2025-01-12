package com.github.pieter_groenendijk.service.reservation;

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
    public Reservation store(Reservation reservation) {
        //TODO: set status to active
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
        Reservation reservation = reservationRepository.retrieveReservationById(reservationId)
                .orElseThrow(() -> new EntityNotFoundException("Reservation with ID " + reservationId + " not found."));

        //reservation.setStatus(ReservationStatus.CANCELLED);
        reservationRepository.updateReservation(reservation);

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
    public void handleUncollectedReservations(long accountId, Date currentDate) throws Exception {
        Membership membership = membershipRepository.retrieveMembershipById(accountId)
                .orElseThrow(() -> new EntityNotFoundException("Account with ID " + accountId + " not found."));

        Account account = membership.getAccount();

        List<Reservation> reservations = reservationRepository.retrieveReservationsByMembershipId(membership.getMembershipId());

        reservations.stream()
                .filter(reservation -> !reservation.getIsCollected(true) && reservation.getReservationPickUpDate().before(currentDate))
                .forEach(reservation -> {
                    reservation.setIsExpired();
                    account.incrementUncollectedReservationCount();
                    reservationRepository.updateReservation(reservation);
                });

        accountRepository.store(account);
    }
    @Override
    public Date getPickupDate(long reservationId) {
        return generateReservationPickUpDate();
    }

    public void markReservationAsLoaned(long reservationId) {
        Reservation reservation = getReservationById(reservationId);
        //TODO: reservation.setStatus(LOAN);
        reservationRepository.updateReservation(reservation);
    }

}
