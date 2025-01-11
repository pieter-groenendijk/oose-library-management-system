package com.github.pieter_groenendijk.service;

import com.github.pieter_groenendijk.model.Account;
import com.github.pieter_groenendijk.model.DTO.ReservationDTO;
import com.github.pieter_groenendijk.model.Membership;
import com.github.pieter_groenendijk.model.Reservation;
import com.github.pieter_groenendijk.model.ReservationStatus;
import com.github.pieter_groenendijk.model.product.ProductCopy;
import com.github.pieter_groenendijk.model.product.ProductCopyStatus;
import com.github.pieter_groenendijk.repository.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
/*
@Nested
class ReservationServiceTest {

    @Mock
    private IReservationRepository reservationRepository;
    @Mock
    private IMembershipRepository membershipRepository;

    @Mock
    private IAccountRepository accountRepository;

    @Mock
    private IProductRepository productRepository;

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

    public ReservationServiceTest() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void store() {
            ReservationDTO reservationDTO = new ReservationDTO();
            reservationDTO.setReservationDate(LocalDate.now());
            reservationDTO.setReservationStatus(ReservationStatus.ACTIVE);
            reservationDTO.setMembershipId(1L);

            ProductCopy mockProductCopy = mock(ProductCopy.class);
            Membership mockMembership = mock(Membership.class);

            when(productRepository.retrieveProductCopyById(1L)).thenReturn(Optional.of(mockProductCopy));
            when(membershipRepository.retrieveMembershipById(1L)).thenReturn(Optional.of(mockMembership));

            Reservation mockReservation = new Reservation();
            mockReservation.setReservationStatus(ReservationStatus.ACTIVE);
            when(reservationRepository.store(any(Reservation.class))).thenReturn(mockReservation);

            ReservationService mockReservationService = spy(reservationService);
            doNothing().when(mockReservationService).handleProductCopyAvailability(any(ProductCopy.class));

            Reservation storedReservation = mockReservationService.store(reservationDTO);

            verify(reservationRepository).store(any(Reservation.class));
            verify(mockReservationService, times(1)).handleProductCopyAvailability(mockProductCopy);
            assertEquals(ReservationStatus.ACTIVE, storedReservation.getReservationStatus());
        }


@Test
    void readyForPickup() {
        Reservation reservation = new Reservation();
        reservation.setReservationDate(LocalDate.now().minusDays(1));
        when(reservationRepository.retrieveReservationById(1L)).thenReturn(Optional.of(reservation));

        assertFalse(reservationService.readyForPickup(1L));
    }

    @Test
    void generateReservationPickUpDate() {
        int pickupDays = 7;
        ProductCopy productCopy = new ProductCopy();
        productCopy.setAvailabilityStatus(ProductCopyStatus.RESERVED);

        LocalDate generatedDate = reservationService.generateReservationPickUpDate(productCopy);

        LocalDate expectedDate = LocalDate.now().plusDays(pickupDays);

        assertEquals(expectedDate, generatedDate, "The generated pickup date is not correct.");
    }


    @Test
    void handleUncollectedReservations() {
        LocalDate currentDate = LocalDate.now();
        Reservation reservation = new Reservation();
        reservation.setReservationPickUpDate(LocalDate.now().minusDays(10));
        reservation.setReservationStatus(ReservationStatus.ACTIVE);

        Account account = new Account();
        account.setAccountId(1L);

        Membership membership = new Membership();
        membership.setMembershipId(1L);
        membership.setAccount(account);

        when(membershipRepository.retrieveMembershipById(1L)).thenReturn(Optional.of(membership));
        when(reservationRepository.retrieveReservationsByMembershipId(1L)).thenReturn(List.of(reservation));

        reservationService.handleUncollectedReservations(1L, currentDate);

        assertEquals(ReservationStatus.EXPIRED, reservation.getReservationStatus());
    }


    @Test
    void markReservationAsLoaned() {
        long reservationId = 1L;
        Reservation reservation = new Reservation();
        reservation.setReservationId(reservationId);
        reservation.setReservationStatus(ReservationStatus.ACTIVE);

        ReservationRepository mockReservationRepository = mock(ReservationRepository.class);
        IAccountRepository mockAccountRepository = mock(IAccountRepository.class);
        IMembershipRepository mockMembershipRepository = mock(IMembershipRepository.class);
        IProductRepository mockProductRepository = mock(IProductRepository.class);
        ReservationService reservationService = new ReservationService(mockReservationRepository, mockMembershipRepository, mockAccountRepository, mockProductRepository);

        when(mockReservationRepository.retrieveReservationById(reservationId)).thenReturn(Optional.of(reservation));

        reservationService.markReservationAsLoaned(reservationId);


        verify(mockReservationRepository).updateReservation(reservation);
        assertEquals(ReservationStatus.LOANED, reservation.getReservationStatus());
    }
}
*/
