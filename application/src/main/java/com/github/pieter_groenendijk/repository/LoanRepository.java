package com.github.pieter_groenendijk.repository;

import com.github.pieter_groenendijk.model.Loan;
import com.github.pieter_groenendijk.model.Reservation;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.util.Optional;

public class LoanRepository implements ILoanRepository {

    SessionFactory sessionFactory;

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
            Loan loan = session.get(Loan.class,membershipId);
            return Optional.ofNullable(loan);
        } catch (HibernateException e) {
            e.printStackTrace();
            return Optional.empty();
        }
    }

    @Override
    public Loan store(Loan loan) {
        Session session = sessionFactory.openSession();

        try {
            session.beginTransaction();
            session.persist(loan);
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
        return loan;
    }

    @Override
    public Loan updateLoan(Loan loan) {
        Session session = sessionFactory.openSession();
        try {
            session.beginTransaction();
            session.merge(loan);
            session.getTransaction().commit();
            return loan;
        } catch (Exception e) {
            if (session.getTransaction() != null) {
                session.getTransaction().rollback();
            }
            e.printStackTrace();
            throw new RuntimeException("Failed to update loan", e);
        } finally {
            session.close();
        }
    }

    @Override
    public Loan deleteLoanByLoanId(long loanId) {
        Session session = sessionFactory.openSession();
        try {
            session.beginTransaction();
            Loan loan = session.get(Loan.class, loanId);
            if (loan != null) {
                session.remove(loan);
                session.getTransaction().commit();
            } else {
                System.out.println("Loan not found with LoanID: " + loanId);
            }
            return loan;
        } catch (Exception e) {
            if (session.getTransaction() != null) {
                session.getTransaction().rollback();
            }
            e.printStackTrace();
            throw new RuntimeException("Failed to delete loan", e);
        } finally {
            session.close();
        }
    }
}
