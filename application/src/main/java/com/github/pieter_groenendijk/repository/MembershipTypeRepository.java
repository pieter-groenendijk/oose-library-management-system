package com.github.pieter_groenendijk.repository;

import com.github.pieter_groenendijk.model.MembershipType;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import java.util.Optional;

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
}
