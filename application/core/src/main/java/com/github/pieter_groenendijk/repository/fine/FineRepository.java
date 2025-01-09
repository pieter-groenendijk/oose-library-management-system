package com.github.pieter_groenendijk.repository.fine;

import com.github.pieter_groenendijk.model.Account;
import com.github.pieter_groenendijk.model.fine.Fine;
import com.github.pieter_groenendijk.model.fine.FineBalance;
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

    @Override
    public void store(Fine fine) throws Exception {
        super.persist(fine);
    }

    @Override
    public Optional<FineBalance> retrieveFineBalance(Account account) throws Exception {
        return super.get(FineBalance.class, account);
    }
}
