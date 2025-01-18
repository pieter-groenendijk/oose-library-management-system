package com.github.pieter_groenendijk.service.loan.event.listener;

import com.github.pieter_groenendijk.model.Loan;
import com.github.pieter_groenendijk.model.event.EventType;
import com.github.pieter_groenendijk.service.event.listener.EventListener;
import com.github.pieter_groenendijk.service.loan.event.scheduling.LoanEventScheduler;
import com.github.pieter_groenendijk.service.notification.NotificationService;

class OverdueEventListener extends EventListener<Loan> {
    private final LoanEventScheduler LOAN_EVENT_SCHEDULER;
    private final NotificationService NOTIFICATION_SERVICE;

    public OverdueEventListener(
        LoanEventScheduler scheduler,
        NotificationService notificationService
    ) {
        super(
            EventType.OVERDUE_LOAN
        );

        this.LOAN_EVENT_SCHEDULER = scheduler;
        this.NOTIFICATION_SERVICE = notificationService;
    }

    @Override
    public void tryReact(Loan loan) throws Exception {
        this.LOAN_EVENT_SCHEDULER.scheduleDayOverdueLoanEvent(loan);

        this.NOTIFICATION_SERVICE.scheduleOverdueLoanNotification(loan);
    }
}