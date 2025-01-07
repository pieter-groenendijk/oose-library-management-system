package com.github.pieter_groenendijk.repository.event;

import com.github.pieter_groenendijk.model.event.Event;
import com.github.pieter_groenendijk.model.event.LoanEvent;

import java.time.LocalDateTime;
import java.util.List;

public interface IEventRepository {
    void storeLoanEvent(LoanEvent event);

    List<Event<?>> retrieveUntil(LocalDateTime scheduledAt);
}
