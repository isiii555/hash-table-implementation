package org.example.utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class DatabaseConnection {
    private static Properties loadProperties() {
        Properties props = new Properties();
        try (FileInputStream input = new FileInputStream("src/main/resources/application.properties")) {
            props.load(input);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        return props;
    }

    public static Connection getConnection() throws SQLException {
        Properties props = loadProperties();
        String url = props.getProperty("url");
        String user = props.getProperty("username");
        String password = props.getProperty("password");
        String driver = props.getProperty("driver");

        try {
            Class.forName(driver);
        } catch (ClassNotFoundException e) {
            System.out.println(e.getMessage());
        }

        return DriverManager.getConnection(url, user, password);
    }
}

