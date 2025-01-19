package com.github.pieter_groenendijk.controller;


import com.github.pieter_groenendijk.exception.EntityNotFoundException;
import com.github.pieter_groenendijk.hibernate.SessionFactoryFactory;
import com.github.pieter_groenendijk.model.DTO.ReservationDTO;
import com.github.pieter_groenendijk.model.Loan;
import com.github.pieter_groenendijk.model.Reservation;
import com.github.pieter_groenendijk.model.event.Event;
import com.github.pieter_groenendijk.repository.*;
import com.github.pieter_groenendijk.repository.loan.ILoanRepository;
import com.github.pieter_groenendijk.repository.loan.LoanRepository;
import com.github.pieter_groenendijk.repository.loan.event.LoanEventRepostory;
import com.github.pieter_groenendijk.repository.scheduling.ITaskRepository;
import com.github.pieter_groenendijk.scheduling.TaskScheduler;
import com.github.pieter_groenendijk.service.loan.event.scheduling.LoanEventScheduler;
import com.github.pieter_groenendijk.service.reservation.IReservationService;
import com.github.pieter_groenendijk.service.reservation.ReservationService;
import com.github.pieter_groenendijk.repository.event.EventRepository;
import com.github.pieter_groenendijk.repository.event.IEventRepository;
import com.github.pieter_groenendijk.service.event.emitting.EventEmitterPool;
import com.github.pieter_groenendijk.service.event.scheduling.EventScheduler;
import com.github.pieter_groenendijk.service.loan.ILoanService;
import com.github.pieter_groenendijk.service.loan.LoanService;
import com.github.pieter_groenendijk.service.loan.event.ILoanEventService;
import com.github.pieter_groenendijk.service.loan.event.LoanEventService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.hibernate.HibernateException;
import org.hibernate.SessionFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;



@RestController
@RequestMapping("/reservation")
public class ReservationController {

    private final SessionFactory sessionFactory = new SessionFactoryFactory().create();
    private final IReservationService reservationService;
    private final ILoanService loanService;


    public ReservationController() {
        // TODO: THIS MESS CAN'T EXIST!!!
        IEventRepository eventRepository = new EventRepository(sessionFactory);
        EventEmitterPool eventEmitterPool = new EventEmitterPool();
        TaskScheduler taskScheduler = new TaskScheduler(1);
        EventScheduler eventScheduler = new EventScheduler((ITaskRepository<Event<?>>) eventRepository, taskScheduler, eventEmitterPool);
        ILoanEventService loanEventService = new LoanEventService(new LoanEventScheduler(
            eventRepository,
            new LoanEventRepostory(sessionFactory),
            eventScheduler
        ));
        IAccountRepository accountRepository = new AccountRepository(sessionFactory);
        IMembershipRepository membershipRepository = new MembershipRepository(sessionFactory);
        IMembershipTypeRepository membershipTypeRepository = new MembershipTypeRepository(sessionFactory);
        IReservationRepository reservationRepository = new ReservationRepository(sessionFactory);
        IProductRepository productRepository = new ProductRepository(sessionFactory);
        ILoanRepository loanRepository = new LoanRepository(sessionFactory);
        this.reservationService = new ReservationService(reservationRepository, membershipRepository, accountRepository, productRepository);
        this.loanService = new LoanService(loanRepository, membershipRepository, loanEventService, reservationService, productRepository, membershipTypeRepository);
    }

    @Operation(summary = "Create a reservation", description = "Create a new reservation")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Reservation created"),
            @ApiResponse(responseCode = "404", description = "ProductCopy or Membership not found"),
    })
    @PostMapping
    public ResponseEntity<?>  store(@RequestBody ReservationDTO reservationDTO) {
        try {
            Reservation reservation = reservationService.store(reservationDTO);
            return ResponseEntity.status(HttpStatus.CREATED).build();
        } catch (EntityNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @Operation(summary = "Get all reservation details by reservationId", description = "Get reservation details by reservationId")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Reservation found"),
            @ApiResponse(responseCode = "404", description = "No reservation found for the given reservationId\"")
    })
    @GetMapping("/{reservationId}")
    public ResponseEntity<Reservation> retrieveReservationById(@PathVariable("reservationId") long reservationId) {
        try {
            Reservation reservation = reservationService.retrieveReservationById(reservationId);
            return new ResponseEntity<>(reservation, HttpStatus.OK);
        } catch (HibernateException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }


    @Operation(summary = "Check if reservation is ready for pickup", description = "Check if reservation is ready for pickup by reservationId")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Ready for pickup status retrieved"),
            @ApiResponse(responseCode = "404", description = "Reservation not found")
    })
    @GetMapping("/{reservationId}/ready")
    public ResponseEntity<Boolean> readyForPickup(@PathVariable("reservationId") long reservationId) {
        boolean isReady = reservationService.readyForPickup(reservationId);
        return new ResponseEntity<>(isReady, HttpStatus.OK);
    }


    @Operation(summary = "Convert reservation to loan", description = "Change the status of the reservation to LOANED")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Reservation converted to loan successfully"),
            @ApiResponse(responseCode = "404", description = "Reservation not found")
    })
    @PutMapping("/{reservationId}/convertToLoan")
    public ResponseEntity<String> markReservationAsLoaned(@PathVariable("reservationId") long reservationId) {
        try {
            Reservation reservation = reservationService.retrieveReservationById(reservationId);
            if (reservation == null) {
                return new ResponseEntity<>("Reservation not found", HttpStatus.NOT_FOUND);
            }

            Loan newLoan = loanService.convertReservationToLoan(reservation);

            return new ResponseEntity<>("Reservation converted to loan successfully with Loan ID: "
                    + newLoan.getLoanId(), HttpStatus.OK);
        } catch (EntityNotFoundException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @Operation(summary = "Cancel a reservation", description = "Change the status of the reservation to CANCELLED")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Reservation cancelled successfully"),
            @ApiResponse(responseCode = "404", description = "Reservation not found")
    })
    @PutMapping("/{reservationId}/cancel")
    public ResponseEntity<String> cancelReservation(@PathVariable("reservationId") long reservationId) {
        try {
            Reservation reservation = reservationService.retrieveReservationById(reservationId);
            if (reservation == null) {
                return new ResponseEntity<>("Reservation not found", HttpStatus.NOT_FOUND);
            }

            reservationService.cancelReservation(reservationId);

            return new ResponseEntity<>("Reservation cancelled successfully", HttpStatus.OK);
        } catch (EntityNotFoundException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }
}


