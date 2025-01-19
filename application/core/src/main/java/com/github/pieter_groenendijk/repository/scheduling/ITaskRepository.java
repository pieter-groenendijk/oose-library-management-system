package com.github.pieter_groenendijk.repository.scheduling;

import com.github.pieter_groenendijk.model.event.Event;
import com.github.pieter_groenendijk.model.scheduling.Task;
import com.github.pieter_groenendijk.scheduling.TaskStatus;

import java.time.LocalDateTime;
import java.util.List;

public interface ITaskRepository<T extends Task> {
    void updateStatus(Task task, TaskStatus status) throws Exception;

    List<T> retrieveUntil(LocalDateTime until) throws Exception;
}
