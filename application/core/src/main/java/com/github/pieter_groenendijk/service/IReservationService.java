package com.github.pieter_groenendijk.service;
import com.github.pieter_groenendijk.model.DTO.ReservationDTO;
import com.github.pieter_groenendijk.model.Membership;
import com.github.pieter_groenendijk.model.Reservation;
import com.github.pieter_groenendijk.model.product.ProductCopy;

import java.time.LocalDate;
import java.util.List;


public interface IReservationService {
    Reservation store(ReservationDTO reservation);
    Reservation retrieveReservationById(long reservationId);
    List<Reservation> reservation(long membershipId);
    Reservation updateReservation(Reservation reservation);
    void cancelReservation(long reservationId);
    boolean readyForPickup(long reservationId);
    LocalDate generateReservationPickUpDate(ProductCopy productCopy);
    void handleUncollectedReservations(long membershipId, LocalDate currentDate);
    void markReservationAsLoaned(long reservationId);
    void handleProductCopyAvailability(ProductCopy productCopy);

    Reservation toEntity(ReservationDTO dto, ProductCopy productCopy, Membership membership);
}
