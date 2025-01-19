package com.github.pieter_groenendijk.repository.scheduling;

import com.github.pieter_groenendijk.model.scheduling.Task;
import com.github.pieter_groenendijk.repository.fine.Repository;
import com.github.pieter_groenendijk.scheduling.TaskStatus;
import org.hibernate.SessionFactory;

public abstract class TaskRepository<T extends Task> extends Repository implements ITaskRepository<T> {
    public TaskRepository(SessionFactory sessionFactory) {
        super(sessionFactory);
    }

    @Override
    public void updateStatus(Task task, TaskStatus status) throws Exception {
        task.setStatus(status);
        super.merge(task);
    }
}

