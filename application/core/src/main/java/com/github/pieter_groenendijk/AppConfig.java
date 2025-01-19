package com.github.pieter_groenendijk;

import com.github.pieter_groenendijk.hibernate.SessionFactoryFactory;
import com.github.pieter_groenendijk.model.event.Event;
import com.github.pieter_groenendijk.repository.*;
import com.github.pieter_groenendijk.repository.event.EventRepository;
import com.github.pieter_groenendijk.repository.event.IEventRepository;
import com.github.pieter_groenendijk.repository.loan.ILoanRepository;
import com.github.pieter_groenendijk.repository.loan.LoanRepository;
import com.github.pieter_groenendijk.repository.loan.event.ILoanEventRepostory;
import com.github.pieter_groenendijk.repository.loan.event.LoanEventRepostory;
import com.github.pieter_groenendijk.repository.scheduling.ITaskRepository;
import com.github.pieter_groenendijk.service.event.emitting.EventEmitterPool;
import com.github.pieter_groenendijk.service.event.scheduling.EventScheduler;
import com.github.pieter_groenendijk.service.loan.ILoanService;
import com.github.pieter_groenendijk.service.loan.LoanService;
import com.github.pieter_groenendijk.service.loan.event.ILoanEventService;
import com.github.pieter_groenendijk.service.loan.event.LoanEventService;
import com.github.pieter_groenendijk.service.loan.event.scheduling.LoanEventScheduler;
import com.github.pieter_groenendijk.service.reservation.IReservationService;
import com.github.pieter_groenendijk.service.reservation.ReservationService;
import com.github.pieter_groenendijk.utils.scheduling.TaskScheduler;
import org.hibernate.SessionFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(basePackages = "com.github.pieter_groenendijk")
public class AppConfig {
    @Bean
    public SessionFactory sessionFactory() {
        return new SessionFactoryFactory().create();
    }
    @Bean
    public ILoanRepository loanRepository() {
        return new LoanRepository(sessionFactory());
    }

    @Bean
    public IMembershipRepository membershipRepository() {
        return new MembershipRepository(sessionFactory());
    }


    @Bean
    public IProductRepository productRepository() {
        return new ProductRepository(sessionFactory());
    }

    @Bean
    public IReservationService reservationService(SessionFactory sessionFactory, IMembershipRepository membershipRepository, IProductRepository productRepository) {
        return new ReservationService(
                new ReservationRepository(sessionFactory),
                membershipRepository,
                new AccountRepository(sessionFactory),
                productRepository
        );
    }

    @Bean
    public ILoanEventService loanEventService(SessionFactory sessionFactory, IEventRepository eventRepository, ILoanEventRepostory loanEventRepostory) {
        return new LoanEventService(
                new LoanEventScheduler(
                        eventRepository,
                        loanEventRepostory,
                        new EventScheduler(
                                (ITaskRepository<Event<?>>) eventRepository,
                                new TaskScheduler(1),
                                new EventEmitterPool()
                        )
                )
        );
    }

    @Bean
    public IEventRepository eventRepository(SessionFactory sessionFactory) {
        return new EventRepository(sessionFactory);
    }

    @Bean
    public ILoanEventRepostory loanEventRepository(SessionFactory sessionFactory) {
        return new LoanEventRepostory(sessionFactory);
    }

    @Bean
    public ILoanService loanService(
            ILoanRepository loanRepository,
            IMembershipRepository membershipRepository,
            ILoanEventService eventService,
            IReservationService reservationService,
            IProductRepository productRepository
    ) {
        return new LoanService(loanRepository, membershipRepository, eventService, reservationService, productRepository);
    }

    @Bean
    public ITaskRepository<Event<?>> taskRepository() {
        return new EventRepository(sessionFactory());
    }

    @Bean
    public EventEmitterPool eventEmitterPool() {
        return new EventEmitterPool();
    }

    @Bean
    public EventScheduler eventScheduler() {
        return new EventScheduler(
                taskRepository(),
                new TaskScheduler(1),
                eventEmitterPool()
        );
    }

    @Bean
    public LoanEventScheduler loanEventScheduler() {
        return new LoanEventScheduler(
                eventRepository(sessionFactory()),
                loanEventRepository(sessionFactory()),
                eventScheduler()
        );
    }

}
