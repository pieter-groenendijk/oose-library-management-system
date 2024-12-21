package com.github.pieter_groenendijk.repository;

import com.github.pieter_groenendijk.model.Reservation;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.util.List;
import java.util.Optional;

public class ReservationRepository implements IReservationRepository {

    private final SessionFactory sessionFactory;

    public ReservationRepository(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;

    }

    @Override
    public Optional<Reservation> retrieveReservationById(long reservationId) {
        Session session = sessionFactory.openSession();
        Reservation reservation;

        try {
            reservation = session.get(Reservation.class, reservationId);
        } finally {
            session.close();
        }
        return Optional.ofNullable(reservation);
    }


    @Override
    public List<Reservation> retrieveReservationByMembershipId(long membershipId) {
        Session session = sessionFactory.openSession();
        try {
            CriteriaBuilder cb = session.getCriteriaBuilder();
            CriteriaQuery<Reservation> cr = cb.createQuery(Reservation.class);
            Root<Reservation> root = cr.from(Reservation.class);

            cr.select(root).where(cb.equal(root.get("membership").get("id"), membershipId));

            return session.createQuery(cr).getResultList();
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Database query failed", e);
        } finally {
            session.close();
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