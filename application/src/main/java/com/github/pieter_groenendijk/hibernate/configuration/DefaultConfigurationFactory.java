package com.github.pieter_groenendijk.hibernate.configuration;

import com.github.pieter_groenendijk.model.*;
import org.hibernate.cfg.Configuration;

public class DefaultConfigurationFactory {
    public Configuration create() {
        ConfigurationBuilder builder = new ConfigurationBuilder();

        setMisc(builder);
        setConnection(builder);

        setAnnotatedClasses(builder);

        return builder.build();
    }

    private void setAnnotatedClasses(ConfigurationBuilder builder) {
        builder
            .addAnnotatedClass(Account.class)
            .addAnnotatedClass(Membership.class)
            .addAnnotatedClass(MembershipType.class)
            .addAnnotatedClass(Lending.class)
            .addAnnotatedClass(NotificationTask.class)
            .addAnnotatedClass(LendingAssociatedNotificationTask.class);
    }

    private void setMisc(ConfigurationBuilder builder) {
        builder
            .setDriver("org.postgresql.Driver")
            .setDialect("org.hibernate.dialect.PostgreSQLDialect")
            .setSchemaHandlingMethod("validate")
            .setPoolSize("10")
            .setFormattingOfSQL(true)
            .setShowingOfSQL(true)
            .setQuotingOfIdentifiers(true);
    }

    private void setConnection(ConfigurationBuilder builder) {
        builder
            .setConnectionURL(
                "postgresql",
                getConnectionHostname(),
                getConnectionPort(),
                getConnectionDatabaseName()
            )
            .setConnectionAuthentication(
                getConnectionUsername(),
                getConnectionPassword()
            );
    }

    private String getConnectionHostname() {
        return System.getenv("DATABASE_HOST");
    }

    private String getConnectionPort() {
        return System.getenv("DATABASE_PORT");
    }

    private String getConnectionDatabaseName() {
        return System.getenv("DATABASE_NAME");
    }

    private String getConnectionUsername() {
        return System.getenv("DATABASE_USER");
    }

    private String getConnectionPassword() {
        return System.getenv("DATABASE_PASSWORD");
    }
}
