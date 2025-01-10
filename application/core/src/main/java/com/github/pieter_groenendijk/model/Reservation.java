package com.github.pieter_groenendijk.model;

import com.github.pieter_groenendijk.model.product.ProductCopy;
import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.Date;

@Entity
@Table(name = "Reservation")
public class Reservation {
    @Id
    @GeneratedValue(strategy = jakarta.persistence.GenerationType.IDENTITY)
    private long reservationId;
    @Column(name = "reservationDate", nullable = false)
    @Temporal(TemporalType.DATE)
    private LocalDate reservationDate;
    @Column(name ="readyForPickup", nullable = false)
    private boolean readyForPickup;
    @Column(name = "reservationPickUpDate")
    @Temporal(TemporalType.DATE)
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

    public void setProductCopyId(ProductCopy productCopyId) {
        this.productCopy = productCopyId;
    }

    public Membership getMembershipId() {
        return membership;
    }

    public void setMembershipId(Membership membershipId) {
        this.membership = membershipId;
    }

    public void setId(long reservationId) {
        this.reservationId = reservationId;
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

    public boolean isReadyForPickup() {
        return readyForPickup;
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
}
