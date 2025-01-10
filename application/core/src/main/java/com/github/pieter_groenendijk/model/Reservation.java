package com.github.pieter_groenendijk.model;

import com.github.pieter_groenendijk.model.product.ProductCopy;
import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name = "Reservation")
public class Reservation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long reservationId;
    @Column(name = "reservationDate", nullable = false)
    private LocalDate reservationDate;
    @Column(name ="readyForPickup", nullable = false)
    private boolean readyForPickup;
    @Column(name = "reservationPickUpDate")
    private LocalDate reservationPickUpDate;
    @OneToOne
    @JoinColumn(name = "productCopyId", nullable = false)
    private ProductCopy productCopy;
    @ManyToOne
    @JoinColumn(name = "membershipId", nullable = false)
    private Membership membership;

    @Enumerated(EnumType.STRING)
    @Column(name = "reservationStatus", nullable = false)
    private ReservationStatus reservationStatus;


    public void setReservationId(Long reservationId) {
        this.reservationId = reservationId;
    }

    public Long getReservationId() {
        return reservationId;
    }

    public void setProductCopy(ProductCopy productCopy) {
        this.productCopy = productCopy;
    }

    public Membership getMembership() {
        return membership;
    }

    public void setMembership(Membership membership) {
        this.membership = membership;
    }

    public void setReservationStatus(ReservationStatus reservationStatus) {
        this.reservationStatus = reservationStatus;
    }

    public LocalDate getReservationDate() {
        return reservationDate;
    }

    public void setReservationDate(LocalDate reservationDate) {
        this.reservationDate = reservationDate;
    }

    public void setReadyForPickup(boolean readyForPickup) {
        this.readyForPickup = readyForPickup;
    }

    public LocalDate getReservationPickUpDate() {
        return reservationPickUpDate;
    }

    public void setReservationPickUpDate(LocalDate reservationPickUpDate) {
        this.reservationPickUpDate = reservationPickUpDate;
    }

    public ReservationStatus getReservationStatus() {
        return reservationStatus;
    }

    public ProductCopy getProductCopy() {
        return productCopy;
    }

}
