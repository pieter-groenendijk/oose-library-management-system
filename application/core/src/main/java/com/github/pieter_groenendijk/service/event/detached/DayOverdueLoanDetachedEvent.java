package com.github.pieter_groenendijk.service.event.detached;

import com.github.pieter_groenendijk.model.event.DayOverdueLoanEvent;
import com.github.pieter_groenendijk.repository.event.IEventRepository;

public class DayOverdueLoanDetachedEvent extends DetachedEvent {
    private final DayOverdueLoanEvent EVENT;

    public DayOverdueLoanDetachedEvent(DayOverdueLoanEvent event) {
        this.EVENT = event;
    }

    @Override
    public void store(IEventRepository repository) {
        repository.storeDayOverdueEvent(
            this.EVENT
        );
    }
}
