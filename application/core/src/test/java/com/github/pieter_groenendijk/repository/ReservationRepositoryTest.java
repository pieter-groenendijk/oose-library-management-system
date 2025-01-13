package com.github.pieter_groenendijk.repository;

import com.github.pieter_groenendijk.model.Reservation;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class ReservationRepositoryTest {
    private SessionFactory sessionFactory;
    private Session session;
    private Transaction transaction;
    private Query<Reservation> query;
    private ReservationRepository reservationRepository;


    @BeforeEach
    void setUp() {
        sessionFactory = mock(SessionFactory.class);
        session = mock(Session.class);
        transaction = mock(Transaction.class);
        query = mock(Query.class);
        when(sessionFactory.openSession()).thenReturn(session);
        when(session.beginTransaction()).thenReturn(transaction);
        when(session.getTransaction()).thenReturn(transaction);
        reservationRepository = new ReservationRepository(sessionFactory);
    }

    @Test
    void retrieveReservationById() {
        long reservationId = 1L;
        Reservation reservation = new Reservation();
        reservation.setReservationId(reservationId);

        when(session.get(Reservation.class, reservationId)).thenReturn(reservation);

        Optional<Reservation> result = reservationRepository.retrieveReservationById(reservationId);

        verify(session).get(Reservation.class, reservationId);
        verify(session).close();

        assertEquals(Optional.of(reservation), result);
    }


    @Test
    void retrieveReservationsByMembershipId() {
        long membershipId = 1L;
        List<Reservation> reservations = List.of(new Reservation(), new Reservation());

        when(session.createQuery("FROM Reservation r WHERE r.membership.id = :membershipId", Reservation.class)).thenReturn(query);
        when(query.setParameter("membershipId", membershipId)).thenReturn(query);
        when(query.getResultList()).thenReturn(reservations);

        List<Reservation> result = reservationRepository.retrieveReservationByMembershipId(membershipId);

        verify(session).createQuery("FROM Reservation r WHERE r.membership.id = :membershipId", Reservation.class);
        verify(query).setParameter("membershipId", membershipId);
        verify(query).getResultList();
        verify(session).close();

        assertEquals(reservations, result);
    }


    @Test
    void store() {
        Reservation reservation = new Reservation();
        reservation.setReservationId(1L);

        doNothing().when(session).persist(reservation);
        doNothing().when(session).flush();
        doNothing().when(transaction).commit();

        Reservation result = reservationRepository.store(reservation);

        verify(session).persist(reservation);
        verify(session).flush();
        verify(transaction).commit();
        verify(session).close();

        assertEquals(reservation, result);

    }

    @Test
    void updateReservation() {
    }
}