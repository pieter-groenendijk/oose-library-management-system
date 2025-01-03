package com.github.pieter_groenendijk.hibernate.configuration;

import com.github.pieter_groenendijk.model.*;
import com.github.pieter_groenendijk.model.notification.LendingAssociatedNotificationTask;
import com.github.pieter_groenendijk.model.notification.NotificationTask;
import com.github.pieter_groenendijk.model.product.PhysicalProductTemplate;
import com.github.pieter_groenendijk.model.product.ProductCopy;
import com.github.pieter_groenendijk.model.product.ProductTemplate;
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
            .addAnnotatedClass(LendingAssociatedNotificationTask.class)
            .addAnnotatedClass(Loan.class)
            .addAnnotatedClass(ProductCopy.class)
            .addAnnotatedClass(ProductTemplate.class)
            .addAnnotatedClass(PhysicalProductTemplate.class);


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
