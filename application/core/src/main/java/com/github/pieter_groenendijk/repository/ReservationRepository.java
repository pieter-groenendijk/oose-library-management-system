package com.github.pieter_groenendijk.repository;

import com.github.pieter_groenendijk.model.DTO.ReservationDTO;
import com.github.pieter_groenendijk.model.Reservation;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.util.ArrayList;
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
    public List<Reservation> retrieveReservationByMembershipId(long membershipId) {
        try (Session session = sessionFactory.openSession()) {
            String hql = "FROM Reservation r WHERE r.membership.id = :membershipId";
            List<Reservation> result = session.createQuery(hql, Reservation.class)
                    .setParameter("membershipId", membershipId)
                    .getResultList();

            return result != null ? result : new ArrayList<>();
        } catch (HibernateException e) {
            e.printStackTrace();
            throw new RuntimeException("Database query failed", e);
        }
    }

    @Override
    public Reservation store(Reservation reservation) {
            Session session = sessionFactory.openSession();
            try {
                System.out.println("Attempting to save reservation: " + reservation);
                session.beginTransaction();
                session.persist(reservation);
                session.flush();
                session.getTransaction().commit();

                System.out.println("Reservation successfully saved: " + reservation);
            } catch (HibernateException e) {

                System.err.println("Error occurred while saving reservation: " + e.getMessage());
                e.printStackTrace();


                if (session.getTransaction() != null) {
                    session.getTransaction().rollback();
                }


                throw new RuntimeException("Error occurred while storing reservation", e);
            } finally {

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