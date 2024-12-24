package com.github.pieter_groenendijk.controller;


import com.github.pieter_groenendijk.hibernate.SessionFactoryFactory;
import com.github.pieter_groenendijk.model.Reservation;
import com.github.pieter_groenendijk.repository.IMembershipRepository;
import com.github.pieter_groenendijk.repository.IReservationRepository;
import com.github.pieter_groenendijk.repository.ReservationRepository;
import com.github.pieter_groenendijk.service.IReservationService;
import com.github.pieter_groenendijk.service.ReservationService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.hibernate.SessionFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;



@RestController
@RequestMapping("/reservation")
public class ReservationController {

    private final SessionFactory sessionFactory = new SessionFactoryFactory().create();
    private final IReservationService reservationService;
    IMembershipRepository membershipRepository;

    public ReservationController() {
        IReservationRepository reservationRepository = new ReservationRepository(sessionFactory);
        reservationService = new ReservationService(reservationRepository, membershipRepository);
    }
    @Operation(summary = "Create a reservation", description = "Create a new reservation")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Reservation created")
    })
    @PostMapping
    public ResponseEntity<Reservation> store(@RequestParam("MembershipId") long membershipId, @RequestParam("ProductCopyId")  long productCopyId) {
        Reservation reservation = reservationService.store(membershipId, productCopyId);
        return new ResponseEntity<>(reservation, HttpStatus.CREATED);
    }


    @Operation(summary = "Get all reservation details by reservationId", description = "Get reservation details by reservationId")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Reservation found"),
            @ApiResponse(responseCode = "204", description = "No reservation found for the given reservationId\"")
    })
    @GetMapping("/{reservationId}")
    public ResponseEntity<Reservation> getReservationById(@PathVariable ("reservationId")long reservationId) {
        Reservation reservation = reservationService.getReservationById(reservationId);
        if (reservation != null) {
            return new ResponseEntity<>(reservation, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
        }
    }

    @Operation(summary = "Update a reservation by reservationId", description = "Get reservation by reservationId")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Reservation found"),
            @ApiResponse(responseCode = "204", description = "No reservation found for the given reservationId\"")
    })
    @PutMapping("/{reservationId}")
    public ResponseEntity<Reservation> updateReservation(@PathVariable("reservationId") long reservationId, @RequestBody Reservation reservation) {
        reservation.setId(reservationId);
        Reservation updatedReservation = reservationService.updateReservation(reservation);
        if (updatedReservation != null) {
            return new ResponseEntity<>(updatedReservation, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
    }

    @Operation(summary = "Delete a reservation by reservationId", description = "Delete reservation by reservationId")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Reservation deleted")
    })
    @DeleteMapping("/{reservationId}")
    public ResponseEntity<Void> deleteReservation(@PathVariable("reservationId") long reservationId) {
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

}


