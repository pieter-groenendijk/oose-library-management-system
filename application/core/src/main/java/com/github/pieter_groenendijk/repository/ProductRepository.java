package com.github.pieter_groenendijk.repository;

import com.github.pieter_groenendijk.model.product.ProductTemplate;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.util.Optional;

public class ProductRepository implements IProductRepository {

    private SessionFactory sessionFactory;

    @Override
    public ProductTemplate store(ProductTemplate product) {
        Session session = sessionFactory.openSession();

        try {
            session.beginTransaction();
            session.persist(product);
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
        return product;

    }

    @Override
    public Optional<ProductTemplate> DeleteProductById(long productId) {
        Session session = null;
        ProductTemplate product = null;

        try {
            session = sessionFactory.openSession();
            session.beginTransaction();

            product = session.get(ProductTemplate.class, productId);
            if (product != null) {
                session.remove(product);
                session.getTransaction().commit();
            } else {
                session.getTransaction().rollback();
            }
        } finally {
            if (session != null) {
                session.close();
            }
        }
        return Optional.ofNullable(product);

    }

    @Override
    public ProductTemplate retrieveProductById(long productId) {
        return null;
    }

    @Override
    public ProductTemplate updateProduct(ProductTemplate product) {
        Session session = sessionFactory.openSession();

        try {
            session.beginTransaction();
            session.merge(product);
            session.getTransaction().commit();
        } catch (Exception e) {
            if (session.getTransaction() != null) {
                session.getTransaction().rollback();
            }
            e.printStackTrace();
        } finally {
            session.close();
        }
        return product;
    }
}


