package com.github.pieter_groenendijk.utils.scheduling;

import com.github.pieter_groenendijk.model.scheduling.Task;

public interface TaskStorage<T extends Task> {
    void store(T task);
}
