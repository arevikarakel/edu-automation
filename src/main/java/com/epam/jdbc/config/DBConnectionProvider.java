package com.epam.jdbc.config;

import com.epam.config.PropertiesReader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnectionProvider {

    private static final Logger logger = LoggerFactory.getLogger(DBConnectionProvider.class);
    private static final PropertiesReader propertiesReader = PropertiesReader
            .getInstance("config.properties");
    private volatile static DBConnectionProvider instance;
    private static Connection connection;

    private DBConnectionProvider() {
        try {
            Class.forName(propertiesReader.getProperty("DB_DRIVER"));
        } catch (ClassNotFoundException e) {
            logger.error("Driver class not found");
        }
    }

    public static DBConnectionProvider getInstance() {
        if (instance == null) {
            synchronized (DBConnectionProvider.class) {
                if (instance == null) {
                    instance = new DBConnectionProvider();
                }
            }
        }
        return instance;
    }

    public Connection getConnection() {
        try {
            if (connection == null || connection.isClosed()) {
                logger.info("Create DB Connection");
                connection = DriverManager.getConnection(
                        propertiesReader.getProperty("DB_URL"),
                        propertiesReader.getProperty("DB_USER"),
                        propertiesReader.getProperty("DB_PASSWORD")
                );
            }
        } catch (SQLException e) {
            logger.error("Can not connect to DB");
        }
        return connection;
    }
}
