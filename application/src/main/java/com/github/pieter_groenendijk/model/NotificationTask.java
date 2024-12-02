package com.github.pieter_groenendijk.model;

import com.github.pieter_groenendijk.services.notifications.send_strategies.registry.SendStrategyType;
import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "NotificationTask")
public class NotificationTask {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(
        name = "notificationTaskId",
        nullable = false
    )
    public Long id;

    @ManyToOne
    @JoinColumn(
        name = "account",
        nullable = false
    )
    public Account account; // TODO: We only really need the id and contact details for sending notifications. Future optimization?


    @Column(
        name = "title",
        nullable = false,
        length = 100
    )
    public String title;

    @Column(
        name = "message",
        nullable = false
    )
    public String message;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(
        name = "scheduledAt",
        nullable = false
    )
    public LocalDateTime scheduledAt;

    @Enumerated(EnumType.STRING)
    @Column(
        name = "sendStrategy",
        nullable = false
    )
    public SendStrategyType sendStrategyType;

    public NotificationTask(
        String title,
        String message,
        SendStrategyType sendStrategyType,
        Account account,
        LocalDateTime scheduledAt
    ) {
        this.title = title;
        this.message = message;
        this.sendStrategyType = sendStrategyType;
        this.account = account;
        this.scheduledAt = scheduledAt;
    }

    public NotificationTask() {} // Needed for ORM

    public boolean isScheduledBefore(LocalDateTime dateTime) {
        return this.scheduledAt.isBefore(dateTime);
    }
}
