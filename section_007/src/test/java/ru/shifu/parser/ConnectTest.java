package ru.shifu.parser;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Properties;
/**
 * ConnectTest
 * @author Pavel Abrikosov(abrikosovp@mail.ru)
 * @version 0.1$
 * @since 0.1
 * 02.01.2019
 */
public class ConnectTest {
    private static final String CONFIG = "tracker.properties";

    public Connection init() {
        try (InputStream in = DBVacancy.class.getClassLoader().getResourceAsStream(CONFIG)) {
            Properties config = new Properties();
            config.load(in);
            Class.forName(config.getProperty("driver"));
            return DriverManager.getConnection(
                    config.getProperty("url"),
                    config.getProperty("user"),
                    config.getProperty("password")
            );
        } catch (Exception e) {
            throw new IllegalStateException(e);
        }
    }
}
