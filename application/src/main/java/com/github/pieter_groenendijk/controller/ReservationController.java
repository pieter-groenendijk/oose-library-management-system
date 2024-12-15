package com.github.pieter_groenendijk.controller;


import com.github.pieter_groenendijk.model.Reservation;

import com.github.pieter_groenendijk.service.IReservationService;

import io.swagger.v3.oas.annotations.Operation;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

@RestController
    @RequestMapping("/api/reservations")
    public class ReservationController {

        private IReservationService reservationService;

        // Create a new reservation
        @Operation(summary = "Create a reservation", description = "Create a new reservation")
        public ResponseEntity<Reservation> createReservation(@RequestParam long membershipId, @RequestParam long copyId) {
            Reservation reservation = reservationService.store(membershipId, copyId);
            return new ResponseEntity<>(reservation, HttpStatus.CREATED);
        }

        // Get reservation by ID
        @GetMapping("/{reservationId}")
        public ResponseEntity<Reservation> getReservationById(@PathVariable long reservationId) {
            Reservation reservation = reservationService.getReservationById(reservationId);
            if (reservation != null) {
                return new ResponseEntity<>(reservation, HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        }

        // Update an existing reservation
        @PutMapping("/{reservationId}")
        public ResponseEntity<Reservation> updateReservation(@PathVariable long reservationId, @RequestBody Reservation reservation) {
            reservation.setId(reservationId);
            Reservation updatedReservation = reservationService.updateReservation(reservation);
            if (updatedReservation != null) {
                return new ResponseEntity<>(updatedReservation, HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        }

        // Delete a reservation
        @DeleteMapping("/{reservationId}")
        public ResponseEntity<Void> deleteReservation(@PathVariable long reservationId) {
            reservationService.removeReservation(reservationId);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        // Check if the reservation is ready for pickup
        @GetMapping("/{reservationId}/ready")
        public ResponseEntity<Boolean> readyForPickup(@PathVariable long reservationId) {
            boolean isReady = reservationService.readyForPickup(reservationId);
            return new ResponseEntity<>(isReady, HttpStatus.OK);
        }

        // Get the pickup date for a reservation
        @GetMapping("/{reservationId}/pickup-date")
        public ResponseEntity<Date> getPickupDate(@PathVariable long reservationId) {
            Date pickupDate = reservationService.getPickupDate(reservationId);
            if (pickupDate != null) {
                return new ResponseEntity<>(pickupDate, HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        }

        @PostMapping("/log-uncollected")
        public ResponseEntity<Void> logUncollectedReservations(@RequestParam long membershipId, @RequestParam Date currentDate) {
            reservationService.logUncollectedReservations(membershipId, currentDate);
            return new ResponseEntity<>(HttpStatus.OK);
        }
}


