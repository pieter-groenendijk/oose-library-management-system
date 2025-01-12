package com.github.pieter_groenendijk.service.loan.event.listener;

import com.github.pieter_groenendijk.model.Loan;
import com.github.pieter_groenendijk.model.event.EventType;
import com.github.pieter_groenendijk.model.fine.Fine;
import com.github.pieter_groenendijk.service.event.listener.EventListener;
import com.github.pieter_groenendijk.service.fine.FineManager;

class DayOverdueEventListener extends EventListener<Loan> {
    private final FineManager FINE_MANAGER;

    public DayOverdueEventListener(FineManager fineManager) {
        super(
            EventType.DAY_OVERDUE_LOAN
        );

        FINE_MANAGER = fineManager;
    }

    @Override
    public void react(Loan loan) {
        // TODO: Schedule New DayOverdueEvent


        // TODO: Declare fine
    }

    private void scheduleNewDayOverdueEvent(Loan loan) {

    }

    private void declareFine() {
//        this.FINE_MANAGER.addFine(
//        );
    }
}