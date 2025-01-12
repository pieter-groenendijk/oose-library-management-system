package com.github.pieter_groenendijk.model.fine;

import com.github.pieter_groenendijk.model.Reservation;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
@DiscriminatorValue(value = "reservation")
public class ReservationFine extends Fine {
    @ManyToOne
    @JoinColumn(
        name = "reservation", nullable = false
    )
    private Reservation reservation;

    public ReservationFine() {}

    public Reservation getReservation() {
        return reservation;
    }

    public void setReservation(Reservation reservation) {
        this.reservation = reservation;
    }
}
