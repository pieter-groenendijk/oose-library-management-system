package com.github.pieter_groenendijk.model;

import jakarta.persistence.Embeddable;

import java.util.Objects;

@Embeddable
public class LendingAssociatedNotificationTaskId {
    private Long lendingId;
    private Long notificationTaskId;

    public LendingAssociatedNotificationTaskId() {} // Empty constructor for ORM

    public LendingAssociatedNotificationTaskId(Long lendingId, Long notificationTaskId) {
        this.lendingId = lendingId;
        this.notificationTaskId = notificationTaskId;
    }

    public Long getNotificationTaskId() {
        return notificationTaskId;
    }

    public void setNotificationTaskId(Long notificationTaskId) {
        this.notificationTaskId = notificationTaskId;
    }

    public Long getLendingId() {
        return lendingId;
    }

    public void setLendingId(Long lendingId) {
        this.lendingId = lendingId;
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) return true;

        if (
            object == null ||
            getClass() != object.getClass()
        ) {
            return false;
        }

        LendingAssociatedNotificationTaskId castObject = (LendingAssociatedNotificationTaskId) object;
        return
            Objects.equals(lendingId, castObject.lendingId) &&
            Objects.equals(notificationTaskId, castObject.notificationTaskId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(
            lendingId,
            notificationTaskId
        );
    }
}
