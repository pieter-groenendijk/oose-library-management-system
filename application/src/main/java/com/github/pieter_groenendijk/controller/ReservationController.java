package com.github.pieter_groenendijk.controller;


import com.github.pieter_groenendijk.model.Reservation;
import com.github.pieter_groenendijk.service.IReservationService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

@RestController
@RequestMapping("/api/reservations")
public class ReservationController {

    private IReservationService reservationService;


    @Operation(summary = "Create a reservation", description = "Create a new reservation")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Reservation created")
    })
    @PostMapping
    public ResponseEntity<Reservation> store(@RequestParam long membershipId, @RequestParam long copyId) {
        Reservation reservation = reservationService.store(membershipId, copyId);
        return new ResponseEntity<>(reservation, HttpStatus.CREATED);
    }

    @Operation(summary = "Get all reservation details by reservationId", description = "Get reservation details by reservationId")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Reservation found"),
            @ApiResponse(responseCode = "204", description = "No reservation found for the given reservationId\"")
    })
    @GetMapping("/{reservationId}")
    public ResponseEntity<Reservation> getReservationById(@PathVariable long reservationId) {
        Reservation reservation = reservationService.getReservationById(reservationId);
        if (reservation != null) {
            return new ResponseEntity<>(reservation, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(null, HttpStatus.OK);
        }
    }

    @Operation(summary = "Update a reservation by reservationId", description = "Get reservation by reservationId")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Reservation found"),
            @ApiResponse(responseCode = "204", description = "No reservation found for the given reservationId\"")
    })
    @PutMapping("/{reservationId}")
    public ResponseEntity<Reservation> updateReservation(@PathVariable long reservationId, @RequestBody Reservation reservation) {
        reservation.setId(reservationId);
        Reservation updatedReservation = reservationService.updateReservation(reservation);
        if (updatedReservation != null) {
            return new ResponseEntity<>(updatedReservation, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.OK);
        }
    }

    @Operation(summary = "Delete a reservation by reservationId", description = "Delete reservation by reservationId")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Reservation deleted")
    })
    @DeleteMapping("/{reservationId}")
    public ResponseEntity<Void> deleteReservation(@PathVariable long reservationId) {
        reservationService.removeReservation(reservationId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @Operation(summary = "Check if reservation is ready for pickup", description = "Check if reservation is ready for pickup by reservationId")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Ready for pickup status retrieved")
    })
    @GetMapping("/{reservationId}/ready")
    public ResponseEntity<Boolean> readyForPickup(@PathVariable long reservationId) {
        boolean isReady = reservationService.readyForPickup(reservationId);
        return new ResponseEntity<>(isReady, HttpStatus.OK);
    }

    @Operation(summary = "Get pickup date for reservation", description = "Get pickup date for reservation by reservationId")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Date found"),
            @ApiResponse(responseCode = "204", description = "No PickupDate for the given reservationId\"")
    })
    @GetMapping("/{reservationId}/pickup-date")
    public ResponseEntity<Date> getPickupDate(@PathVariable long reservationId) {
        Date pickupDate = reservationService.getPickupDate(reservationId);
        if (pickupDate != null) {
            return new ResponseEntity<>(pickupDate, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.OK);
        }
    }

    @Operation(summary = "Log uncollected reservations", description = "Log uncollected reservations for a membership")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Uncollected reservations logged")
    })
    @PostMapping("/log-uncollected")
    public ResponseEntity<Void> logUncollectedReservations(@RequestParam long membershipId, @RequestParam Date currentDate) {
        reservationService.logUncollectedReservations(membershipId, currentDate);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}


