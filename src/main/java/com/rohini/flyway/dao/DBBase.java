package com.rohini.flyway.dao;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

/**
 * Created by IntelliJ IDEA.
 *
 * @author Rohini
 */
public class DBBase {
    public static Connection getConnection() throws ClassNotFoundException, SQLException {
        Connection connection = null;
        try (InputStream inputStream =
                 DBBase.class.getClassLoader().getResourceAsStream("mysql.properties")) {
            Properties properties = new Properties();
            properties.load(inputStream);

            String MYSQL_JDBC_DRIVER = properties.getProperty("jdbc.driver");
            String MYSQL_URL = properties.getProperty("jdbc.url");
            String MYSQL_USERNAME = properties.getProperty("jdbc.username");
            String MYSQL_PASSWORD = properties.getProperty("jdbc.password");

            Class.forName(MYSQL_JDBC_DRIVER);
            connection = DriverManager.getConnection(MYSQL_URL, MYSQL_USERNAME, MYSQL_PASSWORD);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return connection;
    }
}
