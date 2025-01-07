package com.github.pieter_groenendijk.repository.fine;

import com.github.pieter_groenendijk.model.fine.FineType;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.util.Optional;

public class FineRepository extends Repository implements IFineRepository {
    public FineRepository(SessionFactory sessionFactory) {
        super(sessionFactory);
    }

    @Override
    public Optional<FineType> retrieveFineType(String title) {
        FineType fineType;
        try (Session session = this.SESSION_FACTORY.openSession()) {
            try {
                fineType = new FineType(); // TODO: Remove dummy code
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
        return Optional.ofNullable(fineType);
    }
}
