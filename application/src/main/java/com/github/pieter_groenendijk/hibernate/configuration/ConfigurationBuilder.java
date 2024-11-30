package com.github.pieter_groenendijk.hibernate.configuration;

import org.hibernate.cfg.Configuration;

public class ConfigurationBuilder {
    private final Configuration CONFIGURATION;

    public ConfigurationBuilder() {
        this.CONFIGURATION = new Configuration();
    }

    public ConfigurationBuilder addAnnotatedClass(Class annotatedClass) {
        CONFIGURATION.addAnnotatedClass(annotatedClass);

        return this;
    }

    public ConfigurationBuilder setDriver(String driverClass) {
        CONFIGURATION.setProperty(
            "hibernate.connection.driver_class",
            driverClass
        );

        return this;
    }

    public ConfigurationBuilder setDialect(String dialectClass) {
        setProperty(
            "hibernate.dialect",
            dialectClass
        );

        return this;
    }

    public ConfigurationBuilder setSchemaHandlingMethod(String schemaHandlingMethod) {
        setProperty(
            "hibernate.hbm2ddl.auto",
            schemaHandlingMethod
        );

        return this;
    }

    public ConfigurationBuilder setShowingOfSQL(boolean isShown) {
        setProperty(
            "hibernate.show_sql",
            isShown
        );

        return this;
    }

    public ConfigurationBuilder setFormattingOfSQL(boolean isFormatted) {
        setProperty(
            "hibernate.format_sql",
            isFormatted
        );

        return this;
    }

    public ConfigurationBuilder setConnectionAuthentication(String username, String password) {
        setProperty(
            "hibernate.connection.username",
            username
        );

        setProperty(
            "hibernate.connection.password",
            password
        );

        return this;
    }


    public ConfigurationBuilder setConnectionURL(
        String subProtocol,
        String hostName,
        String port,
        String databaseName
    ) {
        setProperty(
            "hibernate.connection.url",
            generateConnectionURL(subProtocol, hostName, port, databaseName)
        );

        return this;
    }

    private String generateConnectionURL(
        String subProtocol,
        String hostName,
        String port,
        String databaseName
    ) {
        return String.format(
            "jdbc:%s://%s:%s/%s",
            subProtocol,
            hostName,
            port,
            databaseName
        );
    }

    public ConfigurationBuilder setPoolSize(String poolSize) {
        setProperty(
            "hibernate.connection.pool_size",
            poolSize
        );

        return this;
    }

    private void setProperty(String key, String value) {
        this.CONFIGURATION.setProperty(key, value);
    }

    private void setProperty(String key, boolean value) {
        this.CONFIGURATION.setProperty(key, value);
    }

    public Configuration build() {
        return CONFIGURATION;
    }
}
