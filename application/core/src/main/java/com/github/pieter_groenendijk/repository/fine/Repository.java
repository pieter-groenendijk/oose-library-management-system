package com.github.pieter_groenendijk.repository.fine;

import org.hibernate.SessionFactory;

public abstract class Repository {
    protected final SessionFactory SESSION_FACTORY;

    public Repository(SessionFactory sessionFactory) {
        this.SESSION_FACTORY = sessionFactory;
    }
}
