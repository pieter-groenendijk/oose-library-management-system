package com.github.pieter_groenendijk.repository.fine;

import org.hibernate.Session;

public interface ReturningAtomicOperation<Result> {
    Result run(Session session);
}
