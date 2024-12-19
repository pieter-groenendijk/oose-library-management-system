package com.github.pieter_groenendijk.model;

import com.github.pieter_groenendijk.model.product.ProductCopy;
import jakarta.persistence.*;

import java.util.Date;

@Entity
@Table(name = "reservation")
public class Reservation {
    @Id
    @GeneratedValue(strategy = jakarta.persistence.GenerationType.IDENTITY)
    private long reservationId;
    @Column(name = "reservationDate", nullable = false)
    @Temporal(TemporalType.DATE)
    private Date reservationDate;

    @Column(name ="readyForPickup", nullable = false)
    private boolean readyForPickup;

    @Column(name = "reservationPickUpDate", nullable = false)
    @Temporal(TemporalType.DATE)
    private Date reservationPickUpDate;

    @ManyToOne
    @JoinColumn(name = "productCopyId", nullable = false)
    private ProductCopy productCopy;
    @ManyToOne
    @JoinColumn(name = "membershipId", nullable = false)
    private Membership membership;

    public void setReservationId(Long reservationId) {
        this.reservationId = reservationId;
    }

    public Long getReservationId() {
        return reservationId;
    }

    public Date getReservationDate() {
        return reservationDate;
    }

    public void setReservationDate(Date reservationDate) {
        this.reservationDate = reservationDate;
    }


    public Membership getMembershipId() {
        return membership;
    }

    public void setMembershipId(Membership membershipId) {
        this.membership = membershipId;
    }

    public void setId(long reservationId) {

    }

}
