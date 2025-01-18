package com.github.pieter_groenendijk.model.event;

import com.github.pieter_groenendijk.model.scheduling.Task;
import jakarta.persistence.*;

@Entity
@Table(name = "Event")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE) // Instances are usually retrieved together as Events. Keeping it in one table keeps this process fast.
@DiscriminatorColumn(
    name = "associationType",
    discriminatorType = DiscriminatorType.STRING,
    length = 50
)
public abstract class Event<Association> extends Task {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(
        name = "eventId",
        nullable = false
    )
    private Long eventId;

    @Enumerated(EnumType.STRING)
    @Column(
        name = "type",
        nullable = false
    )
    protected EventType type;

    public Event() {}

    public EventType getType() {
        return type;
    }

    public void setType(EventType type) {
        this.type = type;
    }

    public abstract Association getAssociation();

    public abstract void setAssociation(Association association);
}
