package com.github.pieter_groenendijk.repository;

import com.github.pieter_groenendijk.model.Membership;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import java.util.Optional;

public class MembershipRepository implements IMembershipRepository{
	private SessionFactory sessionFactory;

	public MembershipRepository(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	public Optional<Membership> retrieveMembershipById(long id) {
		Session session = sessionFactory.openSession();
		Membership membership;

		try {
			membership = session.get(Membership.class, id);
		} finally {
			session.close();
		}
		return Optional.ofNullable(membership);
	}

	public Membership store (Membership membership) {
		Session session = sessionFactory.openSession();

		try {
			session.beginTransaction();
			session.persist(membership);
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
		return membership;
	}
}