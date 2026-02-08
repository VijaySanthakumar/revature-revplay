package com.revplay.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Properties;
import java.io.InputStream;

public class DBConnection {

    private static Connection connection;

    public static Connection getConnection() throws Exception {

        if (connection != null && !connection.isClosed()) {
            return connection;
        }

        // load db.properties
        InputStream input =
                DBConnection.class.getClassLoader().getResourceAsStream("db.properties");

        Properties props = new Properties();
        props.load(input);

        String url = props.getProperty("db.url");
        String username = props.getProperty("db.username");
        String password = props.getProperty("db.password");

        connection = DriverManager.getConnection(url, username, password);

        return connection;
    }
}
