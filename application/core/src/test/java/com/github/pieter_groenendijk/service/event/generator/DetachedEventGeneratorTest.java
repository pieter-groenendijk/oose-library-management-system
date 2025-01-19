package com.github.pieter_groenendijk.service.event.generator;

import com.github.pieter_groenendijk.model.Loan;
import com.github.pieter_groenendijk.model.event.Event;
import com.github.pieter_groenendijk.model.event.EventType;
import com.github.pieter_groenendijk.model.event.LoanEvent;
import com.github.pieter_groenendijk.repository.event.IEventRepository;
import com.github.pieter_groenendijk.scheduling.DetachedTask;
import com.github.pieter_groenendijk.scheduling.TaskStorage;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.MockitoAnnotations.openMocks;

public class DetachedEventGeneratorTest {
    @Mock
    private IEventRepository repository;

    private DetachedEventGenerator<Loan, LoanEvent> generator;

    private abstract class MockDetachedEventGenerator<Association, AssociatedEvent extends Event<Association>> extends DetachedEventGenerator<Association, AssociatedEvent> {
        protected MockDetachedEventGenerator(EventType type) {
            super(
                repository,
                type
            );
        }

        @Override
        protected abstract AssociatedEvent generateEmptyEvent();

        @Override
        protected LocalDateTime determineScheduledDateTime(Association assocation) {
            return LocalDateTime.now();
        }

        @Override
        protected TaskStorage<AssociatedEvent> generateEventStorage() {
            return (loanEvent) -> {

            };
        }
    }

    @BeforeEach
    void beforeEach() {
        openMocks(this);
    }

    @Test
    void testGenerate() {
        this.generator = new MockDetachedEventGenerator<Loan, LoanEvent>(EventType.ALMOST_OVERDUE_LOAN) {
            @Override
            protected LoanEvent generateEmptyEvent() {
                return mock(LoanEvent.class);
            }
        };

        this.generator.generate(mock(Loan.class));
    }

    @Test
    void testGenerate_returnsTask() {
        this.generator = new MockDetachedEventGenerator<Loan, LoanEvent>(EventType.ALMOST_OVERDUE_LOAN) {
            @Override
            protected LoanEvent generateEmptyEvent() {
                return mock(LoanEvent.class);
            }
        };

        DetachedTask<LoanEvent> task = this.generator.generate(mock(Loan.class));

        assertNotNull(task);
    }

    @Test
    void testGenerate_correctScheduledDateTime() {
        LoanEvent returnedLoanEvent = new LoanEvent();

        LocalDateTime returnedScheduledDateTime = LocalDateTime.now();

        this.generator = new MockDetachedEventGenerator<Loan, LoanEvent>(EventType.ALMOST_OVERDUE_LOAN) {
            @Override
            protected LoanEvent generateEmptyEvent() {
                return returnedLoanEvent;
            }

            @Override
            protected LocalDateTime determineScheduledDateTime(Loan loan) {
                return returnedScheduledDateTime;
            }
        };

        this.generator.generate(mock(Loan.class));

        assertEquals(returnedScheduledDateTime, returnedLoanEvent.getScheduledAt());
    }

    @Test
    void testGenerate_correctEventType() {
        LoanEvent returnedLoanEvent = new LoanEvent();

        EventType eventType = EventType.ALMOST_OVERDUE_LOAN;

        this.generator = new MockDetachedEventGenerator<Loan, LoanEvent>(eventType) {
            @Override
            protected LoanEvent generateEmptyEvent() {
                return returnedLoanEvent;
            }
        };

        this.generator.generate(mock(Loan.class));

        assertEquals(eventType, returnedLoanEvent.getType());
    }

    @Test
    void testGenerate_correctAssociation() {
        LoanEvent returnedLoanEvent = new LoanEvent();

        this.generator = new MockDetachedEventGenerator<Loan, LoanEvent>(EventType.ALMOST_OVERDUE_LOAN) {
            @Override
            protected LoanEvent generateEmptyEvent() {
                return returnedLoanEvent;
            }
        };

        Loan association = mock(Loan.class);

        this.generator.generate(association);

        assertEquals(association, returnedLoanEvent.getAssociation());
    }

    // TODO: How to correctly test?
    @Test
    void testGenerate_correctStorage() {}
}
