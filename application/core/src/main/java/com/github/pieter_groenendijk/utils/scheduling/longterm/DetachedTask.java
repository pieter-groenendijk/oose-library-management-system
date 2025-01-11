package com.github.pieter_groenendijk.utils.scheduling.longterm;

import com.github.pieter_groenendijk.model.scheduling.Task;
import com.github.pieter_groenendijk.utils.scheduling.TaskStorage;

/**
 * This class wraps around a task to provide a consistent call signature to store the object, without having to know what
 * specific subclass it is and how it should be stored.
 */
public class DetachedTask<T extends Task> {
    private final T TASK;
    private final TaskStorage<T> STORAGE;

    public DetachedTask(
        T task,
        TaskStorage<T> storage
    ) {
        this.TASK = task;
        this.STORAGE = storage;
    }

    public void store() {
        this.STORAGE.store(
            this.TASK
        );
    }
}
