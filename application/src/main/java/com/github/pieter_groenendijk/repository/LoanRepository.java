package com.github.pieter_groenendijk.repository;

import com.github.pieter_groenendijk.model.Loan;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;


import java.util.Collections;
import java.util.List;
import java.util.Optional;

public class LoanRepository implements ILoanRepository {

    SessionFactory sessionFactory;

    public LoanRepository(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public Optional<Loan> retrieveLoanByLoanId(long loanId) {
        Session session = sessionFactory.openSession();
        Loan loan;
        try {
            loan = session.get(Loan.class, loanId);
        } finally {
            session.close();
        }
        return Optional.ofNullable(loan);
    }


    @Override
    public List<Loan> retrieveActiveLoansByMembershipId(long membershipId) {
        try (Session session = sessionFactory.openSession()) {
            return session.createQuery(
                    "FROM Loan l WHERE l.membership.membershipId = :membershipId", Loan.class)
                    .setParameter("membershipId", membershipId)
                    .list();
        } catch (HibernateException e) {
            e.printStackTrace();
            return Collections.emptyList();
        }
    }


    @Override
    public void store(Loan loan) {
        Session session = sessionFactory.openSession();
        try {
            session.beginTransaction();
            session.persist(loan);
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
    public void updateLoan(Loan loan) {
        Session session = sessionFactory.openSession();
        try {
            session.beginTransaction();
            session.merge(loan);
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
    public void deleteLoanByLoanId(long loanId) {
        Session session = sessionFactory.openSession();
        try {
            session.beginTransaction();
            Loan loan = session.get(Loan.class, loanId);
            session.remove(loan);
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

