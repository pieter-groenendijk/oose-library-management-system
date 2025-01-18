package com.github.pieter_groenendijk.repository.fine;

import org.hibernate.Session;

public interface AtomicOperation {
    void run(Session session);
}
