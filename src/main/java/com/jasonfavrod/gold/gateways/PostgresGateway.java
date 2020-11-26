package com.jasonfavrod.gold.gateways;

import com.jasonfavrod.gold.services.environment.ApplicationEnv;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public abstract class PostgresGateway {
    @Autowired private Environment env;
    @Autowired private ApplicationEnv appEnv;

    protected PostgresGateway() {
        try {
            Class.forName("org.postgresql.Driver");
        }
        catch (ClassNotFoundException cnfe) {
            cnfe.printStackTrace();
        }
    }

    protected Connection getConnection() throws SQLException {
        return DriverManager.getConnection(
            env.getProperty("datasource.url.postgres"),
            appEnv.resolveEnvVar(env.getProperty("datasource.username")),
            appEnv.resolveEnvVar(env.getProperty("datasource.password"))
        );
    }
}
