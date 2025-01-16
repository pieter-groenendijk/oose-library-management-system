package com.github.pieter_groenendijk.model.notification;

import com.github.pieter_groenendijk.model.Account;
import com.github.pieter_groenendijk.model.scheduling.Task;
import com.github.pieter_groenendijk.service.notification.send_strategies.registry.SendStrategyType;
import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "NotificationTask")
public class NotificationTask extends Task {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(
        name = "notificationTaskId",
        nullable = false
    )
    private Long id;

    @ManyToOne
    @JoinColumn(
        name = "account",
        nullable = false
    )
    private Account account; // TODO: We only really need the id and contact details for sending notifications. Future optimization?

    @Column(
        name = "title",
        nullable = false,
        length = 100
    )
    private String title;

    @Column(
        name = "message",
        nullable = false
    )
    private String message;

    @Enumerated(EnumType.STRING)
    @Column(
        name = "sendStrategy",
        nullable = false
    )
    private SendStrategyType sendStrategyType;

    public NotificationTask() {} // Needed for ORM

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public SendStrategyType getSendStrategyType() {
        return sendStrategyType;
    }

    public void setSendStrategyType(SendStrategyType sendStrategyType) {
        this.sendStrategyType = sendStrategyType;
    }

    public boolean isScheduledBefore(LocalDateTime dateTime) {
        return this.getScheduledAt().isBefore(dateTime);
    }
}
