package com.github.pieter_groenendijk.service;

import com.github.pieter_groenendijk.model.Account;
import com.github.pieter_groenendijk.model.Membership;
import com.github.pieter_groenendijk.model.Reservation;
import com.github.pieter_groenendijk.repository.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@Nested
class ReservationServiceTest {

    @Mock
    private IReservationRepository reservationRepository;
    @Mock
    private IMembershipRepository membershipRepository;

    @Mock
    private IAccountRepository accountRepository;

    @InjectMocks
    private ReservationService reservationService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testExample() {
        when(reservationRepository.retrieveReservationById(1L)).thenReturn(Optional.empty());
        assertTrue(true);
    }

    @Test
    void readyForPickup() {
        Reservation reservation = new Reservation();
        reservation.setReservationDate(Date.from(LocalDate.now().minusDays(1).atStartOfDay(ZoneId.systemDefault()).toInstant()));
        when(reservationRepository.retrieveReservationById(1L)).thenReturn(Optional.of(reservation));

        assertFalse(reservationService.readyForPickup(1L));
    }

    @Test
    void generateReservationPickUpDate() {
        int pickupDays = 7;
        Date generatedDate = reservationService.generateReservationPickUpDate();

        LocalDate expectedDate = LocalDate.now().plusDays(pickupDays);
        LocalDate actualDate = generatedDate.toInstant()
                .atZone(ZoneId.systemDefault())
                .toLocalDate();

            assertEquals(expectedDate, actualDate, "The generated pickup date is not correct.");
    }

    @Test
    void getPickupDate() {
        Date expectedDate = Date.from(LocalDate.now().plusDays(7).atStartOfDay(ZoneId.systemDefault()).toInstant());
        Date actualDate = reservationService.getPickupDate(1L);

        assertEquals(expectedDate, actualDate);
    }

    @Test
    void handleUncollectedReservations() {
        Date currentDate = new Date();
        Reservation reservation = new Reservation();
        reservation.setReservationPickUpDate(Date.from(LocalDate.now().minusDays(10).atStartOfDay(ZoneId.systemDefault()).toInstant()));
        reservation.setIsCollected(false);

        Account account = new Account();
        account.setAccountId(1L);

        Membership membership = new Membership();
        membership.setMembershipId(1L);
        membership.setAccount(account);

        when(membershipRepository.retrieveMembershipById(1L)).thenReturn(Optional.of(membership));
        when(reservationRepository.retrieveReservationsByMembershipId(1L)).thenReturn(List.of(reservation));

        reservationService.handleUncollectedReservations(1L, currentDate);

        //verify(reservationRepository).updateReservation(reservation);
        assertTrue(reservation.getIsExpired(true), "The reservation should be marked as expired.");
    }

    @Test
    void store() {
    }

    @Test
    void getReservationById() {
    }

    @Test
    void updateReservation() {
    }

    @Test
    void cancelReservation() {
    }

    @Test
    void markReservationAsLoaned() {
    }
}
