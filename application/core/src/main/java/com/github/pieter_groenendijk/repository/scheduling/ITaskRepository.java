package com.github.pieter_groenendijk.repository.scheduling;

import com.github.pieter_groenendijk.model.scheduling.Task;
import com.github.pieter_groenendijk.utils.scheduling.TaskStatus;

public interface ITaskRepository {
    void updateStatus(Task task, TaskStatus status) throws Exception;
}
