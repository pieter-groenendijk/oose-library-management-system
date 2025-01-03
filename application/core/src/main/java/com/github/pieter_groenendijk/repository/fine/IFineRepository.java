package com.github.pieter_groenendijk.repository.fine;

import com.github.pieter_groenendijk.model.fine.FineType;

import java.util.Optional;

public interface IFineRepository {
    Optional<FineType> retrieveFineType(String title);
}
