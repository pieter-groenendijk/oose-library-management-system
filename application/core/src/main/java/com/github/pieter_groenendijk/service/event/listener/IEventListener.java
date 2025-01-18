package com.github.pieter_groenendijk.service.event.listener;

public interface IEventListener<T> {
    void react(T context);
}
