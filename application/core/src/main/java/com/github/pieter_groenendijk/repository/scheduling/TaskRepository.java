package com.github.pieter_groenendijk.repository.scheduling;

import com.github.pieter_groenendijk.model.scheduling.Task;
import com.github.pieter_groenendijk.repository.fine.Repository;
import com.github.pieter_groenendijk.utils.scheduling.TaskStatus;
import org.hibernate.SessionFactory;

public class TaskRepository extends Repository implements ITaskRepository {
    public TaskRepository(SessionFactory sessionFactory) {
        super(sessionFactory);
    }

    @Override
    public void updateStatus(Task task, TaskStatus status) throws Exception {
        task.setStatus(status);
        super.merge(task);
    }
}
