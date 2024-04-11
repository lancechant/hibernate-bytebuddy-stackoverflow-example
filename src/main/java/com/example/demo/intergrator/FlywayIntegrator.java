package com.example.demo.intergrator;

import org.flywaydb.core.Flyway;
import org.hibernate.boot.Metadata;
import org.hibernate.engine.spi.SessionFactoryImplementor;
import org.hibernate.integrator.spi.Integrator;
import org.hibernate.service.spi.SessionFactoryServiceRegistry;

import javax.sql.DataSource;

public class FlywayIntegrator implements Integrator {

    @Override
    public void integrate(Metadata metadata,
                          SessionFactoryImplementor sessionFactory,
                          SessionFactoryServiceRegistry serviceRegistry) {

        var dataSource = (DataSource) sessionFactory.getProperties().get("hibernate.connection.datasource");
        var defaultSchema = (String) sessionFactory.getProperties().get("hibernate.default_schema");

        new Flyway(Flyway.configure()
                .dataSource(dataSource)
                .defaultSchema(defaultSchema)
                .schemas(defaultSchema))
                .migrate();
    }

    @Override
    public void disintegrate(SessionFactoryImplementor sessionFactory, SessionFactoryServiceRegistry serviceRegistry) {
        // No tear-down required
    }
}
