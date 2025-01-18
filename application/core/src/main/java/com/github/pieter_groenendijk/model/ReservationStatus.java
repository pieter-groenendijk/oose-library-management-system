package com.github.pieter_groenendijk.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;


public enum ReservationStatus {
    ACTIVE,
    LOANED,
    EXPIRED,
    CANCELLED;

}
