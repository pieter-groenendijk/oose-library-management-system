package com.github.pieter_groenendijk.scheduling;

import com.github.pieter_groenendijk.model.scheduling.Task;

public interface TaskStorage<T extends Task> {
    void store(T task) throws Exception;
}
