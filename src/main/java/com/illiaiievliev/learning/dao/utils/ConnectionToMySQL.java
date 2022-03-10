package com.illiaiievliev.learning.dao.utils;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.*;



public class ConnectionToMySQL implements AutoCloseable {
    private static final String URL_KEY = "db.url";
    private static final String USERNAME_KEY = "db.username";
    private static final String PASSWORD_KEY = "db.password";
    private static final Logger log = LogManager.getLogger(ConnectionToMySQL.class);
    private Connection conn;

    public Connection establishConnection() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            log.fatal("Database not connected, driver not loaded", e);
        }

        try {
            conn = DriverManager.getConnection(
                    PropertiesUtil.get(URL_KEY),
                    PropertiesUtil.get(USERNAME_KEY),
                    PropertiesUtil.get(PASSWORD_KEY)
            );
        } catch (SQLException e) {
            log.error("Database not connected", e);
        }
        return conn;
    }

    @Override
    public void close() throws Exception {
        try {
            conn.close();
        } catch (SQLException e) {
            log.error("Database connection not closed", e);
        }
    }
}
