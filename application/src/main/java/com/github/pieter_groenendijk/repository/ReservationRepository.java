package com.github.pieter_groenendijk.repository;

import com.github.pieter_groenendijk.model.Reservation;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
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
    public Reservation store(Reservation reservation) {
        Session session = sessionFactory.openSession();
        try  {
            session.beginTransaction();
            session.persist(reservation);
            session.flush();
            session.getTransaction().commit();
        } catch (Exception e) {
            if (session.getTransaction() != null) {
                session.getTransaction().rollback();
            }
            e.printStackTrace();
        } finally {
            session.close();
        }
        return reservation;
    }

    @Override
    public String updateReservation(Reservation reservation) {
        Session session = sessionFactory.openSession();
        try {
            session.beginTransaction();
            session.merge(reservation);
            session.getTransaction().commit();
            return "Reservation updated successfully";
        } catch (Exception e) {
            if (session.getTransaction() != null) {
                session.getTransaction().rollback();
            }
            e.printStackTrace();
            return "Failed to update reservation";
        } finally {
            session.close();
        }
    }

    @Override
    public Reservation deleteReservationById(long reservationId) {
        Session session = sessionFactory.openSession();
        try {
            session.beginTransaction();
            Reservation reservation = session.get(Reservation.class, reservationId);
            if (reservation != null) {
                session.remove(reservation);
                session.getTransaction().commit();
            } else {
                System.out.println("Reservation not found with ReservationID: " + reservationId);
            }
            return reservation;
        } catch (Exception e) {
            if (session.getTransaction() != null) {
                session.getTransaction().rollback();
            }
            e.printStackTrace();
            throw new RuntimeException("Failed to delete reservation", e);
        } finally {
            session.close();
        }
    }
}