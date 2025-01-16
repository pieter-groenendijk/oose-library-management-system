package com.github.pieter_groenendijk.model.notification;

import com.github.pieter_groenendijk.model.Lending;
import jakarta.persistence.*;

@Entity
@Table(name = "LendingAssociatedNotificationTask")
public class LendingAssociatedNotificationTask {
    @EmbeddedId
    private LendingAssociatedNotificationTaskId id;

    @ManyToOne
    @MapsId("lendingId")
    @JoinColumn(
        name = "lendingId",
        nullable = false
    )
    private Lending lending;

    @ManyToOne
    @MapsId("notificationTaskId")
    @JoinColumn(
        name = "notificationTaskId",
        nullable = false
    )
    private NotificationTask notificationTask;

    public LendingAssociatedNotificationTask() {} // Empty constructor for ORM

    public LendingAssociatedNotificationTask(Lending lending, NotificationTask notificationTask) {
        this.lending = lending;
        this.notificationTask = notificationTask;
        this.id = new LendingAssociatedNotificationTaskId(
            lending.id,
            notificationTask.getId()
        );
    }
}
