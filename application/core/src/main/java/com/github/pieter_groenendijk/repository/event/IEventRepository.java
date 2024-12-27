package com.github.pieter_groenendijk.repository.event;

import com.github.pieter_groenendijk.model.event.DayOverdueLoanEvent;
import com.github.pieter_groenendijk.model.event.Event;

import java.time.LocalDateTime;
import java.util.List;

public interface IEventRepository {
    void storeDayOverdueEvent(DayOverdueLoanEvent event);

    List<Event<?>> retrieveUntil(LocalDateTime scheduledAt);
}
