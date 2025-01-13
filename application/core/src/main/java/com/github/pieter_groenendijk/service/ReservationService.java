package com.github.pieter_groenendijk.service;

import com.github.pieter_groenendijk.exception.EntityNotFoundException;
import com.github.pieter_groenendijk.model.Account;
import com.github.pieter_groenendijk.model.DTO.ReservationDTO;
import com.github.pieter_groenendijk.model.Membership;
import com.github.pieter_groenendijk.model.Reservation;
import com.github.pieter_groenendijk.model.ReservationStatus;
import com.github.pieter_groenendijk.model.product.ProductCopy;
import com.github.pieter_groenendijk.model.product.ProductCopyStatus;
import com.github.pieter_groenendijk.repository.IAccountRepository;
import com.github.pieter_groenendijk.repository.IMembershipRepository;
import com.github.pieter_groenendijk.repository.IProductRepository;
import com.github.pieter_groenendijk.repository.IReservationRepository;

import java.time.LocalDate;
import java.util.List;

import static com.github.pieter_groenendijk.service.ServiceUtils.PICKUP_DAYS;
import static com.github.pieter_groenendijk.service.ServiceUtils.PICKUP_EXPIRY_DAYS;

public class ReservationService implements IReservationService {
    private final IAccountRepository accountRepository;
    private final IReservationRepository reservationRepository;
    private final IMembershipRepository membershipRepository;
    private final IProductRepository productRepository;


    public ReservationService(IReservationRepository reservationRepository, IMembershipRepository membershipRepository, IAccountRepository accountRepository, IProductRepository productRepository) {
        this.reservationRepository = reservationRepository;
        this.membershipRepository = membershipRepository;
        this.accountRepository = accountRepository;
        this.productRepository = productRepository;
    }

    @Override
    public Reservation store(ReservationDTO reservationDTO) {
        if (reservationDTO == null) {
            throw new IllegalArgumentException("Reservation cannot be null");
        }

        ProductCopy productCopy = productRepository.retrieveProductCopyById(reservationDTO.getProductCopyId())
                .orElseThrow(() -> new EntityNotFoundException("ProductCopy not found"));
        Membership membership = membershipRepository.retrieveMembershipById(reservationDTO.getMembershipId())
                .orElseThrow(() -> new EntityNotFoundException("Membership not found"));


        Reservation reservation = toEntity(reservationDTO, productCopy, membership);

        handleProductCopyAvailability(productCopy);

        reservation.setReservationStatus(ReservationStatus.ACTIVE);

        reservationRepository.store(reservation);
        return reservation;
    }

    @Override
    public Reservation retrieveReservationById(long reservationId) {
        return reservationRepository.retrieveReservationById(reservationId)
                .orElseThrow(() -> new EntityNotFoundException("Reservation with ID " + reservationId + " not found."));
    }

    @Override
    public List<Reservation> reservation(long membershipId) {
        return reservationRepository.retrieveReservationByMembershipId(membershipId);
    }

    @Override
    public Reservation updateReservation(Reservation reservation) {
        return reservationRepository.updateReservation(reservation);
    }

    @Override
    public void cancelReservation(long reservationId) {
        Reservation reservation = reservationRepository.retrieveReservationById(reservationId)
                .orElseThrow(() -> new EntityNotFoundException("Reservation with ID " + reservationId + " not found."));

        reservation.setReservationStatus(ReservationStatus.CANCELLED);
        reservationRepository.updateReservation(reservation);

    }

    @Override
    public boolean readyForPickup(long reservationId) {
        return reservationRepository.retrieveReservationById(reservationId)
                .map(reservation -> {
                    LocalDate reservationDate = reservation.getReservationDate();
                    LocalDate pickUpStartDate = reservationDate.plusDays(PICKUP_DAYS);
                    LocalDate pickUpEndDate = reservationDate.plusDays(PICKUP_EXPIRY_DAYS);
                    LocalDate today = LocalDate.now();

                    return !today.isBefore(pickUpStartDate) && !today.isAfter(pickUpEndDate);
                })
                .orElse(false);
    }

    @Override
    public LocalDate generateReservationPickUpDate(ProductCopy productCopy) {
        if (productCopy.getAvailabilityStatus() == ProductCopyStatus.AVAILABLE) {
            return LocalDate.now().plusDays(PICKUP_DAYS);
        } else if (productCopy.getAvailabilityStatus() == ProductCopyStatus.LOANED) {
            return null;
        }
        return LocalDate.now();
    }
    @Override
    public void handleProductCopyAvailability(ProductCopy productCopy) {
        if (productCopy.getAvailabilityStatus() == ProductCopyStatus.AVAILABLE) {
            LocalDate reservationPickUpDate = generateReservationPickUpDate(productCopy);
            Reservation reservation = reservationRepository.retrieveReservationById(productCopy.getProductCopyId())
                    .orElseThrow(() -> new EntityNotFoundException("Reservation not found for ProductCopy ID " + productCopy.getProductCopyId()));
            reservation.setReservationPickUpDate(reservationPickUpDate);
            productCopy.setAvailabilityStatus(ProductCopyStatus.RESERVED);
            reservationRepository.updateReservation(reservation);
            productRepository.updateProductCopy(productCopy);
        }
    }

    @Override
    public void handleUncollectedReservations(long accountId, LocalDate currentDate) {
        Membership membership = getMembership(accountId);
        Account account = membership.getAccount();
        List<Reservation> reservations = reservationRepository.retrieveReservationByMembershipId(membership.getMembershipId());
        for (Reservation reservation : reservations) {
            if (shouldExpireReservation(reservation, currentDate)) {
                expireReservation(reservation, account);
            }
        }
        accountRepository.store(account);
    }

    @Override
    public void markReservationAsLoaned(long reservationId) {
        Reservation reservation = retrieveReservationById(reservationId);
        reservation.setReservationStatus(ReservationStatus.LOANED);
        reservationRepository.updateReservation(reservation);
    }
    private Membership getMembership(long accountId) {
        return membershipRepository.retrieveMembershipById(accountId)
                .orElseThrow(() -> new EntityNotFoundException("Account with ID " + accountId + " not found."));
    }

    private boolean shouldExpireReservation(Reservation reservation, LocalDate currentDate) {
        return isReservationEligibleForExpiration(reservation) && hasPickupDatePassed(reservation, currentDate);
    }

    private boolean isReservationEligibleForExpiration(Reservation reservation) {
        return !reservation.getReservationStatus().equals(ReservationStatus.CANCELLED)
                && !reservation.getReservationStatus().equals(ReservationStatus.EXPIRED);
    }

    private boolean hasPickupDatePassed(Reservation reservation, LocalDate currentDate) {
        return reservation.getReservationPickUpDate().isBefore(currentDate);
    }

    private void expireReservation(Reservation reservation, Account account) {
        reservation.setReservationStatus(ReservationStatus.EXPIRED);
        account.incrementUncollectedReservationCount();
        reservationRepository.updateReservation(reservation);
    }

    public Reservation toEntity(ReservationDTO dto, ProductCopy productCopy, Membership membership) {
        Reservation reservation = new Reservation();
        reservation.setReservationDate(dto.getReservationDate());
        reservation.setReservationPickUpDate(null);
        reservation.setReadyForPickup(dto.isReadyForPickup());
        reservation.setProductCopy(productCopy);
        reservation.setMembership(membership);
        reservation.setReservationStatus(dto.getReservationStatus());
        return reservation;
    }

}
