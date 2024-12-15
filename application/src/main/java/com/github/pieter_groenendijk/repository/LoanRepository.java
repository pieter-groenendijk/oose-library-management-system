package com.github.pieter_groenendijk.repository;

import com.github.pieter_groenendijk.model.Loan;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.util.Optional;

public class LoanRepository implements ILoanRepository {

    SessionFactory sessionFactory;

    public LoanRepository(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public Optional<Loan> retrieveLoanByLoanId(long loanId) {
        try (Session session = sessionFactory.openSession()) {
            Loan loan = session.get(Loan.class, loanId);
            return Optional.ofNullable(loan);
        } catch (HibernateException e) {
            e.printStackTrace();
            return Optional.empty();
        }
    }

    @Override
    public Optional<Loan> retrieveLoanByMembershipId(long membershipId) {
        try (Session session = sessionFactory.openSession()) {
            Loan loan = session.get(Loan.class, membershipId);
            return Optional.ofNullable(loan);
        } catch (HibernateException e) {
            e.printStackTrace();
            return Optional.empty();
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
    public String updateLoan(Loan loan) {
        Session session = sessionFactory.openSession();
        try {
            session.beginTransaction();
            session.merge(loan);
            session.getTransaction().commit();
            return "Loan with LoanID: " + loan.getLoanId() + " updated successfully";
        } catch (HibernateException e) {
            if (session.getTransaction() != null) {
                session.getTransaction().rollback();
            }
            e.printStackTrace();
            return "Failed to update loan";
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

