package ru.shifu.parser;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.quartz.Job;
import org.quartz.JobExecutionContext;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
/**
 * Jobs

 * @author Pavel Abrikosov(abrikosovp@mail.ru)
 * @version 0.1$
 * @since 0.1
 * 18.12.2018
 */
public class Jobs implements Job {

    private static final Logger LOGGER = LogManager.getLogger(StartParser.class);
    private Configurator configurator = new Configurator();
    private HTMLParser parse = new HTMLParser(this.connectDB());


    @Override
    public void execute(JobExecutionContext jobExecutionContext) {
        this.LOGGER.info("Parser starting.");
        this.parse.parse();
        this.LOGGER.info("Stop parsing.");
    }
    /**
     * Connect to the database.
     */
    public Connection connectDB() {
        try {
            return DriverManager.getConnection(this.configurator.getValue("jdbc.url"),
                    this.configurator.getValue("jdbc.username"),
                    this.configurator.getValue("jdbc.password"));
        } catch (SQLException e) {
            throw new IllegalStateException(e);
        }
    }
}
