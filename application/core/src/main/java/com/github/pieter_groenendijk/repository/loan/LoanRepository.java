package com.github.pieter_groenendijk.repository.loan;

import com.github.pieter_groenendijk.model.Loan;
import com.github.pieter_groenendijk.model.LoanStatus;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.criteria.CriteriaUpdate;
import java.util.List;
@Repository
public class LoanRepository implements ILoanRepository {

    SessionFactory sessionFactory;

    @Autowired
    public LoanRepository(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public Loan retrieveLoanByLoanId(long loanId) {
        try (Session session = sessionFactory.openSession()) {
            CriteriaBuilder cb = (CriteriaBuilder) session.getCriteriaBuilder();
            CriteriaQuery<Loan> cr = cb.createQuery(Loan.class);
            Root<Loan> root = cr.from(Loan.class);

            cr.select(root).where(cb.equal(root.get("loanId"), loanId));

            return (Loan) session.createQuery((CriteriaUpdate) cr).uniqueResult();
        } catch (HibernateException e) {
            e.printStackTrace();
            throw new RuntimeException("Database query failed for Loan ID: " + loanId, e);
        }
    }

    @Override
    public List<Loan> retrieveActiveLoansByMembershipId(long membershipId) {
        try (Session session = sessionFactory.openSession()) {
            CriteriaBuilder cb = (CriteriaBuilder) session.getCriteriaBuilder();
            CriteriaQuery<Loan> cr = cb.createQuery(Loan.class);
            Root<Loan> root = cr.from(Loan.class);

            cr.select(root)
                    .where(
                            cb.equal(root.get("membership").get("id"), membershipId),
                            cb.equal(root.get("loanStatus"), LoanStatus.ACTIVE)
                    );

            return session.createQuery((CriteriaUpdate) cr).getResultList();
        } catch (HibernateException e) {
            e.printStackTrace();
            throw new RuntimeException("Database query failed", e);
        }
    }

    @Override
    public Loan store(Loan loan)  {
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            session.persist(loan);
            session.getTransaction().commit();
            System.out.println("Loan successfully saved: " + loan);
        } catch (HibernateException e) {
            e.printStackTrace();
            throw new RuntimeException("Error occurred while storing loan", e);
        }
        return loan;

    }

    @Override
    public void updateLoan(Loan loan) {
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            session.merge(loan);
            session.getTransaction().commit();

        } catch (HibernateException e) {
            e.printStackTrace();
            throw new RuntimeException("Failed to update loan", e);
        }
    }

    @Override
    public List<Loan> retrieveAllActiveLoans() {
        try (Session session = sessionFactory.openSession()) {
            CriteriaBuilder cb = (CriteriaBuilder) session.getCriteriaBuilder();
            CriteriaQuery<Loan> cr = cb.createQuery(Loan.class);
            Root<Loan> root = cr.from(Loan.class);

            cr.select(root)
                    .where(
                            cb.equal(root.get("loanStatus"), LoanStatus.ACTIVE)
                    );

            return session.createQuery((CriteriaUpdate) cr).getResultList();
        } catch (HibernateException e) {
            e.printStackTrace();
            throw new RuntimeException("Database query failed", e);
        }
    }
}

