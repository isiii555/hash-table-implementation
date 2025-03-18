package org.example;

import liquibase.Liquibase;
import liquibase.database.Database;
import liquibase.database.DatabaseFactory;
import liquibase.database.jvm.JdbcConnection;
import liquibase.resource.ClassLoaderResourceAccessor;

import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Properties;

public class Main {
    public static void main(String[] args) throws Exception {
//        HashTableController controller = new HashTableController();

        Properties props = new Properties();
        try (FileInputStream fis = new FileInputStream("src/main/resources/application.properties")) {
            props.load(fis);
        }

        String url = props.getProperty("url");
        String username = props.getProperty("username");
        String password = props.getProperty("password");
        String changelogFile = props.getProperty("changeLogFile");

        Connection connection = DriverManager.getConnection(url, username, password);

        Database database = DatabaseFactory.getInstance()
                .findCorrectDatabaseImplementation(new JdbcConnection(connection));

        Liquibase liquibase = new Liquibase(changelogFile, new ClassLoaderResourceAccessor(),database);

        liquibase.update("main");
        connection.close();

    }
}
