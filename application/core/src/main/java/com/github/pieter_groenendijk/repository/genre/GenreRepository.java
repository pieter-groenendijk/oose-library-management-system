package com.github.pieter_groenendijk.repository.genre;

import com.github.pieter_groenendijk.model.product.Genre;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import java.util.Optional;
import java.util.List;
import java.util.Collections;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;
import org.hibernate.HibernateException;
import org.springframework.stereotype.Repository;

import javax.persistence.criteria.CriteriaUpdate;

@Repository
public class GenreRepository implements IGenreRepository {
    private SessionFactory sessionFactory;

    public GenreRepository (SessionFactory sessionFactory) {this.sessionFactory = sessionFactory;}

    public Optional<Genre> retrieveGenreById(long id) {
        Session session = sessionFactory.openSession();
        Genre genre;
        try{
            genre = session.get(Genre.class, id);
        } finally {
            session.close();
        }
        return Optional.ofNullable(genre);
    }

    public void store(Genre genre) {
        Session session = sessionFactory.openSession();
        try {
            session.beginTransaction();
            session.persist(genre);
            session.flush();

            session.getTransaction().commit();
        } catch (HibernateException e) {
            if (session.getTransaction() != null ) {
                session.getTransaction().rollback();
            }
            e.printStackTrace();
        } finally {
            session.close();
        }
    }

    public void update (Genre genre) {
        Session session = sessionFactory.openSession();

        try {
            session.beginTransaction();
            session.merge(genre);
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

    public List retrieveGenreList() {
        Session session = sessionFactory.openSession();
        try {
            CriteriaBuilder cb = (CriteriaBuilder) session.getCriteriaBuilder();
            CriteriaQuery<Genre> cr = cb.createQuery(Genre.class);
            Root<Genre> root = cr.from(Genre.class);
            cr.select(root);
            return session.createQuery((CriteriaUpdate) cr).getResultList();
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