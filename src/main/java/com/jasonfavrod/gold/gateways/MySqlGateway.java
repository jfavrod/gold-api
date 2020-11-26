package com.jasonfavrod.gold.gateways;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Logger;

public abstract class MySqlGateway {
    @Autowired private Environment env;
    @Autowired protected Logger logger;

    protected MySqlGateway() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        }
        catch (ClassNotFoundException cnfe) {
            cnfe.printStackTrace();
        }
    }

    protected Connection getConnection() throws SQLException {
        return DriverManager.getConnection(
            env.getProperty("datasource.url.mysql"),
            env.getProperty("datasource.username"),
            env.getProperty("datasource.password")
        );
    }
}
