package com.github.pieter_groenendijk.repository;

import com.github.pieter_groenendijk.model.Reservation;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.util.Date;
import java.util.Optional;

public class ReservationRepository implements IReservationRepository {

    private final SessionFactory sessionFactory;

    public ReservationRepository(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public Optional<Reservation> retrieveReservationById(long reservationId) {
        try (Session session = sessionFactory.openSession()) {
            Reservation reservation = session.get(Reservation.class, reservationId);
            return Optional.ofNullable(reservation);
        } catch (HibernateException e) {
            e.printStackTrace();
            return Optional.empty();
        }
    }

    @Override
    public Optional<Reservation> retrieveReservationByMembershipId(long membershipId) {
        try (Session session = sessionFactory.openSession()) {
            Reservation reservation = session.get(Reservation.class, membershipId);
            return Optional.ofNullable(reservation);
        } catch (HibernateException e) {
            e.printStackTrace();
            return Optional.empty();
        }
    }

    @Override
    public void store(Reservation reservation) {
        Session session = sessionFactory.openSession();
        try {
            session.beginTransaction();
            session.persist(reservation);
            session.flush();
            session.getTransaction().commit();
        } catch (HibernateException e) {
            if (session.getTransaction() != null) {
                session.getTransaction().rollback();
            }
            e.printStackTrace();
        } finally {
            session.close();
        }
    }

    @Override
    public void updateReservation(Reservation reservation) {
        Session session = sessionFactory.openSession();
        try {
            session.beginTransaction();
            session.merge(reservation);
            session.getTransaction().commit();
        } catch (HibernateException e) {
            if (session.getTransaction() != null) {
                session.getTransaction().rollback();
            }
            e.printStackTrace();
        } finally {
            session.close();
        }
    }

    @Override
    public void deleteReservationById(long reservationId) {
        Session session = sessionFactory.openSession();
        try {
            session.beginTransaction();
            Reservation reservation = session.get(Reservation.class, reservationId);
            session.remove(reservation);
            session.getTransaction().commit();
        } catch (HibernateException e) {
            if (session.getTransaction() != null) {
                session.getTransaction().rollback();
            }
            e.printStackTrace();
        } finally {
            session.close();
        }
    }

}



