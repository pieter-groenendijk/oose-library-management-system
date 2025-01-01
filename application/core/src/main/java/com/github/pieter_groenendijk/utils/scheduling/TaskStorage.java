package com.github.pieter_groenendijk.utils.scheduling;

public interface TaskStorage<T extends Task> {
    void store(T task);
}
