package com.github.pieter_groenendijk.model.notification;

import com.github.pieter_groenendijk.model.Account;
import com.github.pieter_groenendijk.service.notification.send_strategies.registry.SendStrategyType;
import com.github.pieter_groenendijk.service.notification.task.NotificationTaskStatus;
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

    @Enumerated(EnumType.STRING)
    @Column(
        name = "status",
        nullable = false
    )
    public NotificationTaskStatus status;

    public NotificationTask(
        String title,
        String message,
        Account account,
        LocalDateTime scheduledAt,
        SendStrategyType sendStrategyType,
        NotificationTaskStatus status
    ) {
        this.title = title;
        this.message = message;
        this.sendStrategyType = sendStrategyType;
        this.account = account;
        this.scheduledAt = scheduledAt;
        this.status = status;
    }

    public NotificationTask(
        String title,
        String message,
        Account account,
        LocalDateTime scheduledAt,
        SendStrategyType sendStrategyType
    ) {
        this(
            title,
            message,
            account,
            scheduledAt,
            sendStrategyType,
            NotificationTaskStatus.PENDING
        );
    }

    public NotificationTask() {} // Needed for ORM

    public boolean isScheduledBefore(LocalDateTime dateTime) {
        return this.scheduledAt.isBefore(dateTime);
    }
}
