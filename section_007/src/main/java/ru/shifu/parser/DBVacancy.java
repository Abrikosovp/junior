package ru.shifu.parser;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.Closeable;
import java.sql.*;
import java.time.LocalDateTime;
import java.time.Month;
import java.util.LinkedList;
import java.util.List;


/**
 * Class for working with DB.
 * Accepts a list of vacancies and stores them in a database.
 * @author Pavel Abrikosov(abrikosovp@mail.ru)
 * @version 0.1$
 * @since 0.1
 * 31.12.2018
 */
public class DBVacancy implements Closeable {

    private final LocalDateTime defaultMaxDate = LocalDateTime.of(2018, Month.JANUARY, 1, 0, 0);
    /**
     * Logger for info output.
     */
    private static final Logger LOGGER = LogManager.getLogger(DBVacancy.class);
    /**
     * Connection to the database.
     */
    private Connection conn;
    /**
     * Loaded properties with connection options.
     */
    private Configurator config;


    public DBVacancy(Connection conn) {
        this.config = new Configurator();
        this.conn = conn;
        this.createStructure(config);
    }

    public DBVacancy() { }

    /**
     * Add vacancy to database.
     * @param vacancy vacancy.
     */
    public boolean addVacancy(Vacancy vacancy) {
        Boolean valid = false;
        try (PreparedStatement st = this.conn.prepareStatement("INSERT INTO vacancies(header, href, date) values (?, ?, ?) ON CONFLICT(href) DO NOTHING")) {
            st.setString(1, vacancy.getName());
            st.setString(2, vacancy.getHref());
            st.setTimestamp(3, Timestamp.valueOf(vacancy.getDate()));
            st.addBatch();
            st.executeBatch();
            this.LOGGER.info(String.format("vacancy %s - %s - %s", vacancy.getDate(), vacancy.getName(), vacancy.getHref()));
            valid = true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return valid;
    }
    /**
     * Return the list of vacancies.
     * @return the list of vacancies.
     */
    public List<Vacancy> getAll() {
        List<Vacancy> result = new LinkedList<>();
        try (PreparedStatement st = this.conn.prepareStatement("SELECT * FROM vacancies")) {
            try (ResultSet rs = st.executeQuery()) {
                while (rs.next()) {
                    Timestamp date = rs.getTimestamp("date");
                    Vacancy vacancy = new Vacancy(rs.getString("header"),
                            rs.getString("href"),
                            date.toLocalDateTime()
                    );
                    result.add(vacancy);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    /**
     * Method adds multiple vacancies to the database.
     * @param vacancies list of vacancies.
     */
    public void addMultiVac(List<Vacancy> vacancies) {
        int added = 0;
        for (Vacancy vacancy : vacancies) {
            if (addVacancy(vacancy)) {
                added++;
            }
        }
        this.LOGGER.info(String.format("Added %s new vacancies.", added));
    }


    /**
     * Method gets the date of the last check from the database.
     * @return date of last check.
     */
    public LocalDateTime getMaxDate() {
        Timestamp result = null;
        try (PreparedStatement st = this.conn.prepareStatement("SELECT max(date) as maxDate FROM vacancies")) {
            try (ResultSet rs = st.executeQuery()) {
                if (rs.next()) {
                    result = rs.getTimestamp("maxDate");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result == null ? this.defaultMaxDate : result.toLocalDateTime();
    }

    /**
     * Creates a database structure if it does not exist.
     */
    private void createStructure(Configurator settings) {
        String table = settings.getValue("sql.table_init");
        try (Statement st = this.conn.createStatement()) {
            st.executeUpdate(table);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void close() {
        if (this.conn != null) {
            try {
                this.conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}