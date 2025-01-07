package com.github.pieter_groenendijk.repository.fine;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

public abstract class Repository {
    protected final SessionFactory SESSION_FACTORY;

    public Repository(SessionFactory sessionFactory) {
        this.SESSION_FACTORY = sessionFactory;
    }

    protected <T> void get(Class<T> entityType, Object id) throws Exception {
        this.performAtomicOperation(session -> session.get(entityType, id)); // TODO: A get should not have to use transaction like this...
    }

    protected void remove(Object object) throws Exception {
        this.performAtomicOperation(session -> session.remove(object));
    }

    protected void merge(Object object) throws Exception {
        this.performAtomicOperation(session -> session.merge(object));
    }

    protected void persist(Object object) throws Exception {
        this.performAtomicOperation(session -> session.persist(object));
    }

    protected void performAtomicOperation(AtomicOperation operation) throws Exception {
        try (Session session = SESSION_FACTORY.openSession()) {
            Transaction transaction = session.beginTransaction();
            try {
                this.tryAtomicOperation(
                    session,
                    transaction,
                    operation
                );
            } catch (Exception exception) {
                this.catchAtomicOperationException(
                    transaction,
                    exception
                );
            }
        }
    }

    private void tryAtomicOperation(
        Session session,
        Transaction transaction,
        AtomicOperation operation
    ) {
        operation.run(session);

        session.flush();
        transaction.commit();
    }

    private void catchAtomicOperationException(Transaction transaction, Exception exception) throws Exception {
        logAtomicOperationException(exception);
        transaction.rollback();

        throw exception;
    }

    // TODO: Implement logging
    private void logAtomicOperationException(Exception exception) {
        exception.printStackTrace();
    }
}
