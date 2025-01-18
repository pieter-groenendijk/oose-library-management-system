package com.github.pieter_groenendijk.service.fine.type;

import com.github.pieter_groenendijk.model.fine.FineType;
import com.github.pieter_groenendijk.repository.fine.IFineRepository;

/**
 * This class provides a way of mapping between the static defined fine types and those retrieved at runtime.
 */
public class FineTypeRegistry {
    private FineType DAY_OVERDUE_LENDING_FINE_TYPE;
    private FineType UNCOLLECTED_RESERVATION_PATTERNS_FINE_TYPE;

    public FineTypeRegistry() {}

    public void initialize(IFineRepository repository) throws Exception {
        this.DAY_OVERDUE_LENDING_FINE_TYPE = repository.retrieveFineType("day-overdue-lending").orElseThrow();
        this.UNCOLLECTED_RESERVATION_PATTERNS_FINE_TYPE = repository.retrieveFineType("uncollected-reservation-pattern").orElseThrow();
    }

    public FineType fromStaticFineType(StaticFineType staticFineType) {
        return switch(staticFineType) {
            case DAY_OVERDUE_LENDING -> DAY_OVERDUE_LENDING_FINE_TYPE;
            case UNCOLLECTED_RESERVATION_PATTERN -> UNCOLLECTED_RESERVATION_PATTERNS_FINE_TYPE;
        };
    }
}
