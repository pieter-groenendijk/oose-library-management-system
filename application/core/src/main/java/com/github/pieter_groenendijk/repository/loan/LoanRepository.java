package com.github.pieter_groenendijk.repository.loan;

import com.github.pieter_groenendijk.model.Loan;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

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
        Session session = sessionFactory.openSession();
        try {
            CriteriaBuilder cb = session.getCriteriaBuilder();
            CriteriaQuery<Loan> cr = cb.createQuery(Loan.class);
            Root<Loan> root = cr.from(Loan.class);

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

