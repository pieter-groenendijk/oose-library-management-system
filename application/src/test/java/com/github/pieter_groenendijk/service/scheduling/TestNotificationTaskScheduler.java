package com.github.pieter_groenendijk.service.scheduling;

import com.github.pieter_groenendijk.TaskScheduler;
import com.github.pieter_groenendijk.model.notification.NotificationTask;
import com.github.pieter_groenendijk.repository.notification.INotificationTaskRepository;
import com.github.pieter_groenendijk.service.notification.scheduling.NotificationTaskScheduler;
import com.github.pieter_groenendijk.service.notification.send_strategies.registry.NotificationSendStrategyRegistry;
import com.github.pieter_groenendijk.service.notification.task.UnprocessedNotificationTask;
import org.junit.jupiter.api.BeforeEach;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.junit.jupiter.api.Test;
import org.mockito.MockitoAnnotations;

import java.time.LocalDateTime;

import static org.mockito.Mockito.*;

public class TestNotificationTaskScheduler {
    @Mock
    private TaskScheduler scheduler;

    @Mock
    private INotificationTaskRepository repository;

    @Mock
    private NotificationSendStrategyRegistry sendStrategyRegistry;

    private NotificationTaskScheduler taskScheduler;

    @BeforeEach
    public void beforeEach() {
        MockitoAnnotations.openMocks(this);

        taskScheduler = spy(new NotificationTaskScheduler(scheduler, repository, sendStrategyRegistry));
    }

    @Test
    public void testSchedule_StoresTheUnprocessedTask() {
        UnprocessedNotificationTask unprocessedTask = mock(UnprocessedNotificationTask.class);
        NotificationTask task = mock(NotificationTask.class);
        when(unprocessedTask.store()).thenReturn(task);

        taskScheduler.schedule(unprocessedTask);

        verify(unprocessedTask).store();
    }

    // TODO: Not sure if this is a good test, since it really just checks if a method is called, not if the domain is correct
    @Test
    public void testSchedule_ScheduleDirectlyInMemory() {
        UnprocessedNotificationTask unprocessedTask = mock(UnprocessedNotificationTask.class);
        NotificationTask task = mock(NotificationTask.class);
        when(unprocessedTask.store())
            .thenReturn(task);
        when(task.isScheduledBefore(any(LocalDateTime.class)))
            .thenReturn(true);
        task.scheduledAt = LocalDateTime.now();

        taskScheduler.schedule(unprocessedTask);

        verify(scheduler, times(1))
            .schedule(any(Runnable.class), any(LocalDateTime.class));
    }

    // TODO: Not sure if this is a good test, since it really just checks if a method is called, not if the domain is correct
    @Test
    public void testSchedule_ShouldNotScheduleDirectlyInMemory() {
        UnprocessedNotificationTask unprocessedTask = mock(UnprocessedNotificationTask.class);
        NotificationTask task = mock(NotificationTask.class);
        when(unprocessedTask.store())
            .thenReturn(task);
        when(task.isScheduledBefore(any(LocalDateTime.class)))
            .thenReturn(false);
        task.scheduledAt = LocalDateTime.now();

        taskScheduler.schedule(unprocessedTask);

        verify(scheduler, times(0))
            .schedule(any(Runnable.class), any(LocalDateTime.class));
    }

    // TODO: Add test that checks if the task will actually be executed?
}
