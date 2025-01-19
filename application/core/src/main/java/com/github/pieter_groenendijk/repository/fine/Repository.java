package com.github.pieter_groenendijk.repository.fine;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import java.io.Serializable;
import java.util.Optional;
@org.springframework.stereotype.Repository
public abstract class Repository {
    protected final SessionFactory SESSION_FACTORY;

    public Repository(SessionFactory sessionFactory) {
        this.SESSION_FACTORY = sessionFactory;
    }

    protected <T> Optional<T> get(Class<T> entityType, Object id) throws Exception {
        return this.performAtomicOperationReturning(session -> {
            return Optional.ofNullable(
                session.get(entityType, (Serializable) id)
            );
        });
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

    protected <Result> Result performAtomicOperationReturning(ReturningAtomicOperation<Result> operation) throws Exception {
        try (Session session = SESSION_FACTORY.openSession()) {
            Transaction transaction = session.beginTransaction();
            try {
                return this.tryReturningAtomicOperation(
                    session,
                    transaction,
                    operation
                );
            } catch (Exception exception) {
                this.catchAtomicOperationException(transaction, exception);
                throw exception;
            }
        }
    }

    private <Result> Result tryReturningAtomicOperation(
        Session session,
        Transaction transaction,
        ReturningAtomicOperation<Result> operation
    ) {
        Result result = operation.run(session);
        commitAtomicOperation(session, transaction);

        return result;
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
        commitAtomicOperation(session, transaction);
    }

    private void commitAtomicOperation(
        Session session,
        Transaction transaction
    ) {
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
