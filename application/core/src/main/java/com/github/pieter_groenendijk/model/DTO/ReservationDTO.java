package com.github.pieter_groenendijk.model.DTO;

import com.github.pieter_groenendijk.model.ReservationStatus;

import java.time.LocalDate;

public class ReservationDTO {
    private LocalDate reservationDate = LocalDate.now();
    private boolean readyForPickup = false;
    private long productCopyId;
    private long membershipId;
    private ReservationStatus reservationStatus;

    // Getters and Setters
    public LocalDate getReservationDate() {
        return reservationDate;
    }

    public void setReservationDate(LocalDate reservationDate) {
        this.reservationDate = reservationDate;
    }

    public boolean isReadyForPickup() {
        return readyForPickup;
    }

    public void setReadyForPickup(boolean readyForPickup) {
        this.readyForPickup = readyForPickup;
    }


    public long getMembershipId() {
        return membershipId;
    }

    public void setMembershipId(long membershipId) {
        this.membershipId = membershipId;
    }

    public ReservationStatus getReservationStatus() {
        return reservationStatus;
    }

    public void setReservationStatus(ReservationStatus reservationStatus) {
        this.reservationStatus = reservationStatus;
    }

    public long getProductCopyId() {
        return productCopyId;
    }

    public void setProductCopyId(long productCopyId) {
        this.productCopyId = productCopyId;
    }
}
