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
    @OneToOne
    @JoinColumn(name = "productCopyId", nullable = false)
    private ProductCopy productCopy;
    @ManyToOne
    @JoinColumn(name = "membershipId", nullable = false)
    private Membership membership;

    @Column(name = "isExpired", nullable = false)
    private boolean isExpired = false;

    @Column(name = "isCollected", nullable = false)
    private boolean isCollected = false;


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

    }

    public Date getReservationPickUpDate() {
        return reservationPickUpDate;
    }
    public void setReservationPickUpDate(Date reservationPickUpDate) {
        this.reservationPickUpDate = reservationPickUpDate;
    }

    public boolean getIsCollected(boolean isCollected) {
        return isCollected;
    }

    public boolean getIsExpired(boolean isExpired) {
        return isExpired;
    }

    public void setIsCollected(boolean isCollected) {
        this.isCollected = isCollected;
    }

    public void setIsExpired() {
        this.isExpired = true;
    }

}
