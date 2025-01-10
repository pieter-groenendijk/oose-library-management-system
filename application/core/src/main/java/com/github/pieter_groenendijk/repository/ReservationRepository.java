package com.github.pieter_groenendijk.repository;

import com.github.pieter_groenendijk.model.DTO.ReservationDTO;
import com.github.pieter_groenendijk.model.Reservation;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.util.List;
import java.util.Optional;

public class ReservationRepository implements IReservationRepository {

    private SessionFactory sessionFactory;

    public ReservationRepository(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public Optional<Reservation> retrieveReservationById(long reservationId) {
        Session session = sessionFactory.openSession();
        Reservation reservation;

        try {
            reservation = session.get(Reservation.class, reservationId);
        }finally {
            session.close();
        }
        return Optional.ofNullable(reservation);
    }


    @Override
    public List<Reservation> retrieveReservationsByMembershipId(long membershipId) {
        try (Session session = sessionFactory.openSession()) {
            String hql = "FROM Reservation r WHERE r.membership.id = :membershipId";
            return session.createQuery(hql, Reservation.class)
                    .setParameter("membershipId", membershipId)
                    .getResultList();
        } catch (HibernateException e) {
            e.printStackTrace();
            throw new RuntimeException("Database query failed", e);
        }
    }

    @Override
    public Reservation store(Reservation reservation) {
            Session session = sessionFactory.openSession();
            try {
                // Log the reservation to verify the incoming data
                System.out.println("Attempting to save reservation: " + reservation);

                session.beginTransaction();
                session.persist(reservation);  // Persist the reservation
                session.flush();               // Ensure the transaction is processed immediately
                session.getTransaction().commit();  // Commit the transaction

                System.out.println("Reservation successfully saved: " + reservation);
            } catch (HibernateException e) {
                // Log the exception with details to understand the cause
                System.err.println("Error occurred while saving reservation: " + e.getMessage());
                e.printStackTrace();  // Or use a logger

                // If there was a transaction, rollback
                if (session.getTransaction() != null) {
                    session.getTransaction().rollback();
                }

                // Propagate the exception further
                throw new RuntimeException("Error occurred while storing reservation", e);
            } finally {
                // Close the session to avoid resource leaks
                session.close();
            }
            return reservation;

    }
        @Override
    public Reservation updateReservation(Reservation reservation) {
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
        return reservation;
    }


}