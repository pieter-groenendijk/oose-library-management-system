package com.github.pieter_groenendijk.repository;

import com.github.pieter_groenendijk.model.Membership;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import java.util.Optional;
import java.util.List;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;

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

	public List<Membership> retrieveMembershipsByAccountId(long accountId) {
		Session session = sessionFactory.openSession();
		try {
			CriteriaBuilder cb = session.getCriteriaBuilder();
			CriteriaQuery<Membership> cr = cb.createQuery(Membership.class);
			Root<Membership> root = cr.from(Membership.class);

			cr.select(root).where(cb.equal(root.get("account").get("id"), accountId));

			return session.createQuery(cr).getResultList();
		} catch (HibernateException e) {
			if (session.getTransaction() != null) {
				session.getTransaction().rollback();
			}
			e.printStackTrace();
		} finally {
			session.close();
		}
	}


	public Membership store (Membership membership) {
		Session session = sessionFactory.openSession();

		try {
			session.beginTransaction();
			session.persist(membership);
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
		return membership;
	}
}