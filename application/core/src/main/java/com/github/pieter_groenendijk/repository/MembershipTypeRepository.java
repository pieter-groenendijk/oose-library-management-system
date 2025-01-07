package com.github.pieter_groenendijk.repository;

import com.github.pieter_groenendijk.model.MembershipType;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import java.util.Optional;
import com.github.pieter_groenendijk.model.DTO.MembershipRequestDTO;

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

    public MembershipType store(MembershipType membershipType) {
        Session session = sessionFactory.openSession();
        try {
            session.beginTransaction();
            session.persist(membershipType);
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
        return membershipType;
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

    public MembershipType update(MembershipType membershipType) {
        Session session = sessionFactory.openSession();

        try {
            session.beginTransaction();
            session.merge(membershipType);
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
        return membershipType;
    }

    public Optional<MembershipType> deleteMembershipTypeById(long id) {
        Session session = null;
        MembershipType membershipType = null;

        try {
            session = sessionFactory.openSession();
            session.beginTransaction();

            membershipType = session.get(MembershipType.class, id);
            if (membershipType != null) {
                session.delete(membershipType);
                session.getTransaction().commit();
            } else {
                session.getTransaction().rollback();
            }
        } finally {
            if (session != null) {
                session.close();
            }
        }
        return Optional.ofNullable(membershipType);
    }
}
