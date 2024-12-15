package com.github.pieter_groenendijk.repository;


import com.github.pieter_groenendijk.model.Reservation;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.util.Optional;

public class ReservationRepository implements IReservationRepository {

    private SessionFactory sessionFactory;
    public ReservationRepository(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }
    @Override
    public Optional<Reservation> retrieveReservationById(long reservationId) {
        try (Session session = sessionFactory.openSession()) {
            return Optional.ofNullable(session.get(Reservation.class, reservationId));
        } catch (Exception e) {
            e.printStackTrace();
            return Optional.empty();
        }
    }

    @Override
    public Optional<Reservation> retrieveReservationByMembershipId(long membershipId) {
        try (Session session = sessionFactory.openSession()) {
            return Optional.ofNullable(session.get(Reservation.class, membershipId));
        } catch (Exception e) {
            e.printStackTrace();
            return Optional.empty();
        }
    }

    @Override
    public Reservation store(Reservation reservation) {
        return null;
    }

    @Override
    public Reservation updateReservation(Reservation reservation) {
        return null;
    }

    @Override
    public Reservation deleteReservationById(long reservationId) {
        return null;
    }
}
