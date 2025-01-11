package com.github.pieter_groenendijk.model.event;

import com.github.pieter_groenendijk.model.Reservation;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
@DiscriminatorValue("reservation")
public class ReservationEvent extends Event<Reservation> {
    @ManyToOne
    @JoinColumn(
        name = "reservation",
        nullable = false
    )
    private Reservation reservation;

    public ReservationEvent() {}

    @Override
    public Reservation getAssociation() {
        return null;
    }

    @Override
    public void setAssociation(Reservation reservation) {

    }
}
