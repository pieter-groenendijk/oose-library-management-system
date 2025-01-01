package com.github.pieter_groenendijk.repository.event;

import com.github.pieter_groenendijk.model.event.Event;
import com.github.pieter_groenendijk.model.event.LoanEvent;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

public class EventRepository implements IEventRepository {
    @Override
    public void storeLoanEvent(LoanEvent event) {

    }

    @Override
    public List<Event<?>> retrieveUntil(LocalDateTime scheduledAt) {
        return List.of();
    }
}
