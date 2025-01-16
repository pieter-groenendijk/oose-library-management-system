package com.github.pieter_groenendijk.service.notification.task;

import com.github.pieter_groenendijk.model.Account;
import com.github.pieter_groenendijk.model.Loan;
import com.github.pieter_groenendijk.model.notification.LoanNotification;
import com.github.pieter_groenendijk.repository.notification.INotificationRepository;
import com.github.pieter_groenendijk.service.notification.generators.AlmostOverdueLoanNotificationGenerator;
import com.github.pieter_groenendijk.service.notification.generators.OverdueLoanNotificationGenerator;
import com.github.pieter_groenendijk.utils.scheduling.longterm.DetachedTask;

public class DetachedNotificationFactory {
    private final OverdueLoanNotificationGenerator OVERDUE_GENERATOR;
    private final AlmostOverdueLoanNotificationGenerator ALMOST_OVERDUE_GENERATOR;

    public DetachedNotificationFactory(
        INotificationRepository repository
    ) {
        this.OVERDUE_GENERATOR = new OverdueLoanNotificationGenerator(repository);
        this.ALMOST_OVERDUE_GENERATOR = new AlmostOverdueLoanNotificationGenerator(repository);
    }

    public DetachedTask<LoanNotification> createOverdueLoanNotification(Account account, Loan loan) {
        return OVERDUE_GENERATOR.generate(
            account,
            loan
        );
    }

    public DetachedTask<LoanNotification> createAlmostOverdueLoanNotification(Account account, Loan loan) {
        return this.ALMOST_OVERDUE_GENERATOR.generate(
            account,
            loan
        );
    }
}

