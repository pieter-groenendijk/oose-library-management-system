package com.github.pieter_groenendijk.hibernate;

import com.github.pieter_groenendijk.hibernate.configuration.DefaultConfigurationFactory;
import org.hibernate.SessionFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Component
public class SessionFactoryFactory {
    @Bean
    public SessionFactory create() {
        return new DefaultConfigurationFactory()
            .create()
            .buildSessionFactory();
    }
}
