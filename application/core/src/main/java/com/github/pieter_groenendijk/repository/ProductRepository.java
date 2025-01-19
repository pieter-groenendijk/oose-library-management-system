package com.github.pieter_groenendijk.repository;
import com.github.pieter_groenendijk.model.product.ProductCopy;
import com.github.pieter_groenendijk.model.product.ProductTemplate;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;
import org.hibernate.Transaction;
import org.hibernate.Hibernate;

import java.util.Optional;
@Repository
public class ProductRepository implements IProductRepository {

    SessionFactory sessionFactory;

    public ProductRepository(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

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
    public Optional<ProductTemplate> deleteProductById(long productId) {
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
    public Optional<ProductTemplate> retrieveProductById(long productId) {
        Session session = sessionFactory.openSession();
        ProductTemplate product;

        try {
            product = session.get(ProductTemplate.class, productId);
        } finally {
            session.close();
        }
        return Optional.ofNullable(product);
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

    public ProductCopy updateProductCopy(ProductCopy productCopy) {
        Session session = sessionFactory.openSession();

        try {
            session.beginTransaction();
            session.merge(productCopy);
            session.getTransaction().commit();
        } catch (Exception e) {
            if (session.getTransaction() != null) {
                session.getTransaction().rollback();
            }
            e.printStackTrace();
        } finally {
            session.close();
        }
        return productCopy;
    }

    public Optional<ProductCopy> retrieveProductCopyById(long id) {
        try (Session session = sessionFactory.openSession()) {
            Transaction transaction = session.beginTransaction();
            try {
                ProductCopy productCopy = session.get(ProductCopy.class, id);
                transaction.commit();
                return Optional.ofNullable(productCopy);
            } catch (Exception e) {
                transaction.rollback();
                throw e;
            }
        }
    }
}


