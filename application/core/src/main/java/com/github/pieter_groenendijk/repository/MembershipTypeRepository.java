package com.github.pieter_groenendijk.repository;

import com.github.pieter_groenendijk.model.MembershipType;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import java.util.Optional;
import com.github.pieter_groenendijk.model.DTO.MembershipRequestDTO;
import java.util.List;
import java.util.Collections;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;
import org.hibernate.HibernateException;
import com.github.pieter_groenendijk.model.LendingLimit;
import org.springframework.stereotype.Repository;

@Repository
public class MembershipTypeRepository implements IMembershipTypeRepository {

    private SessionFactory sessionFactory;

    public MembershipTypeRepository (SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public Optional<MembershipType> retrieveMembershipTypeById(long id) {
        Session session = sessionFactory.openSession();
        MembershipType membershipType;

        try {
            membershipType = session.get(MembershipType.class, id);
        } finally {
            session.close();
        }
        return Optional.ofNullable(membershipType);
    }

    public void store(MembershipType membershipType) {
        Session session = sessionFactory.openSession();
        try {
            session.beginTransaction();
            session.persist(membershipType);
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

    public boolean doesMembershipTypeExistByDescription(String description){
        Session session = sessionFactory.openSession();
        MembershipType membershipType;

        try {
            String hql = "FROM MembershipType a WHERE a.description = :description";
            membershipType = session.createQuery(hql, MembershipType.class)
                    .setParameter("description", description)
                    .uniqueResult();
        } finally {
            session.close();
        }

        return membershipType != null;
    }

    public void update(MembershipType membershipType) {
        Session session = sessionFactory.openSession();

        try {
            session.beginTransaction();
            session.merge(membershipType);
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

    public List<MembershipType> retrieveMembershipTypeList() {
            Session session = sessionFactory.openSession();
            try {
                CriteriaBuilder cb = session.getCriteriaBuilder();
                CriteriaQuery<MembershipType> cr = cb.createQuery(MembershipType.class);
                Root<MembershipType> root = cr.from(MembershipType.class);
                cr.select(root);
                return session.createQuery(cr).getResultList();
            } catch (HibernateException e) {
                if (session.getTransaction() != null) {
                    session.getTransaction().rollback();
                }
                e.printStackTrace();
                return Collections.emptyList();
            } finally {
                session.close();
            }
    }

    public Optional<LendingLimit> retrieveLendingLimitById(long id) {
        Session session = sessionFactory.openSession();
        LendingLimit lendingLimit;

        try {
            lendingLimit = session.get(LendingLimit.class, id);
        } finally {
            session.close();
        }
        return Optional.ofNullable(lendingLimit);
    }

    public void store(LendingLimit lendingLimit){
        Session session = sessionFactory.openSession();
        try {
            session.beginTransaction();
            session.persist(lendingLimit);
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

    public void update(LendingLimit lendingLimit){
        Session session = sessionFactory.openSession();

        try {
            session.beginTransaction();
            session.merge(lendingLimit);
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

    public List<LendingLimit> retrieveLendingLimitList(long id){
        Session session = sessionFactory.openSession();
        try {
            CriteriaBuilder cb = session.getCriteriaBuilder();
            CriteriaQuery<LendingLimit> cr = cb.createQuery(LendingLimit.class);
            Root<LendingLimit> root = cr.from(LendingLimit.class);

            cr.select(root).where(cb.equal(root.get("membershiptype").get("id"), id));

            return session.createQuery(cr).getResultList();
        } catch (HibernateException e) {
            if (session.getTransaction() != null) {
                session.getTransaction().rollback();
            }
            e.printStackTrace();
            return Collections.emptyList();
        } finally {
            session.close();
        }
    }

}
