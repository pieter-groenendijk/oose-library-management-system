package com.github.pieter_groenendijk.repository;

import com.github.pieter_groenendijk.model.Loan;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.util.List;

public class LoanRepository implements ILoanRepository {

    SessionFactory sessionFactory;

    public LoanRepository(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public Loan retrieveLoanByLoanId(long loanId) {
        try (Session session = sessionFactory.openSession()) {
            CriteriaBuilder cb = session.getCriteriaBuilder();
            CriteriaQuery<Loan> cr = cb.createQuery(Loan.class);
            Root<Loan> root = cr.from(Loan.class);

            cr.select(root).where(cb.equal(root.get("id"), loanId));

            return session.createQuery(cr).uniqueResult(); // Return single loan or null
        } catch (HibernateException e) {
            e.printStackTrace();
            throw new RuntimeException("Database query failed for Loan ID: " + loanId, e);
        }
    }

    @Override
    public List<Loan> retrieveActiveLoansByMembershipId(long membershipId) {
        Session session = sessionFactory.openSession();
        try {
            CriteriaBuilder cb = session.getCriteriaBuilder();
            CriteriaQuery<Loan> cr = cb.createQuery(Loan.class);
            Root<Loan> root = cr.from(Loan.class);

            cr.select(root)
                    .where(
                            cb.equal(root.get("membership").get("id"), membershipId),
                            cb.equal(root.get("loanStatus"), "ACTIVE")
                    );

            return session.createQuery(cr).getResultList();
        } catch (HibernateException e) {
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
        } catch (HibernateException e) {
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

        } catch (HibernateException e) {
            if (session.getTransaction() != null) {
                session.getTransaction().rollback();
            }
            e.printStackTrace();

        } finally {
            session.close();
        }
        return loan;
    }
}

