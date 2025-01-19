package com.github.pieter_groenendijk.service;

import com.github.pieter_groenendijk.exception.EntityNotFoundException;
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

import static com.github.pieter_groenendijk.service.ServiceUtils.PICKUP_DAYS;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

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

    private static final long RESERVATION_ID = 1L;
    private static final long PRODUCT_COPY_ID = 1L;
    private static final int PICKUP_DAYS = 7;



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
    void testStoreReservationDTO() {
        ReservationDTO reservationDTO = new ReservationDTO();
        reservationDTO.setProductCopyId(1L);
        reservationDTO.setMembershipId(1L);
        reservationDTO.setReservationDate(LocalDate.now());
        reservationDTO.setReservationStatus(ReservationStatus.ACTIVE);

        ProductCopy productCopy = new ProductCopy();
        productCopy.setProductCopyId(1L);

        Membership membership = new Membership();
        membership.setMembershipId(1L);

        when(productRepository.retrieveProductCopyById(1L)).thenReturn(Optional.of(productCopy));
        when(membershipRepository.retrieveMembershipById(1L)).thenReturn(Optional.of(membership));

        Reservation reservation = new Reservation();
        when(reservationRepository.store(any(Reservation.class))).thenReturn(reservation);

        Reservation result = reservationService.store(reservationDTO);

        assertNotNull(result);
        assertEquals(ReservationStatus.ACTIVE, result.getReservationStatus());
        verify(productRepository).retrieveProductCopyById(1L);
        verify(membershipRepository).retrieveMembershipById(1L);
        verify(reservationRepository).store(any(Reservation.class));
    }
    @Test
    void testStore_NullReservationDTO() {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            reservationService.store(null);
        });
        assertEquals("Reservation cannot be null", exception.getMessage());
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
        ProductCopy productCopy = new ProductCopy();
        productCopy.setAvailabilityStatus(ProductCopyStatus.AVAILABLE);

        LocalDate today = LocalDate.now();
        LocalDate expectedDate = today.plusDays(PICKUP_DAYS);
        LocalDate actualDate = reservationService.generateReservationPickUpDate(productCopy);

        assertEquals(expectedDate, actualDate, "The generated pickup date for AVAILABLE status is not correct.");
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



    @Test
    void testReadyForPickup_reservationNotFound() {
        when(reservationRepository.retrieveReservationById(RESERVATION_ID)).thenReturn(Optional.empty());

        boolean result = reservationService.readyForPickup(RESERVATION_ID);

        assertFalse(result);
    }

    @Test
    void testGenerateReservationPickUpDate_productCopyAvailable() {
        ProductCopy productCopy = mock(ProductCopy.class);

        when(productCopy.getAvailabilityStatus()).thenReturn(ProductCopyStatus.AVAILABLE);

        LocalDate expectedDate = LocalDate.now().plusDays(PICKUP_DAYS);
        LocalDate result = reservationService.generateReservationPickUpDate(productCopy);

        assertEquals(expectedDate, result);
    }

    @Test
    void testGenerateReservationPickUpDate_productCopyLoaned() {
        ProductCopy productCopy = mock(ProductCopy.class);

        when(productCopy.getAvailabilityStatus()).thenReturn(ProductCopyStatus.LOANED);

        Exception exception = assertThrows(IllegalStateException.class, () -> {
            reservationService.generateReservationPickUpDate(productCopy);
        });

        assertEquals("This product cannot be picked up because it is already loaned.", exception.getMessage());
    }

    @Test
    void testHandleProductCopyAvailability_reservationNotFound() {
        ProductCopy productCopy = mock(ProductCopy.class);

        when(productCopy.getAvailabilityStatus()).thenReturn(ProductCopyStatus.AVAILABLE);
        when(productCopy.getProductCopyId()).thenReturn(PRODUCT_COPY_ID);
        when(reservationRepository.retrieveReservationById(PRODUCT_COPY_ID)).thenReturn(Optional.empty());

        Exception exception = assertThrows(EntityNotFoundException.class, () -> {
            reservationService.handleProductCopyAvailability(productCopy);
        });

        assertEquals("Reservation not found for ProductCopy ID " + PRODUCT_COPY_ID, exception.getMessage());
    }
}

