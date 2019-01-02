package ru.shifu.parser;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Before;
import org.junit.Test;
import ru.shifu.tracker.ConnectionRollback;


import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.Month;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;
/**
 * DBVacancyTest
 * @author Pavel Abrikosov(abrikosovp@mail.ru)
 * @version 0.1$
 * @since 0.1
 * 02.01.2019
 */
public class DBVacancyTest {

 private static final Logger LOG = LogManager.getLogger(DBVacancyTest.class);
 private static final String CONFIG = "tracker.properties";
 private final LocalDateTime first = LocalDateTime.of(2018, Month.JANUARY, 1, 0, 0);
 private final LocalDateTime second = LocalDateTime.of(2018, Month.JANUARY, 2, 0, 0);
 private Vacancy junior;
 private Vacancy middle;


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

    @Before
    public void beforeTest() {
    this.junior = new Vacancy("Java developer", "www.job4j.ru", this.first);
    this.middle = new Vacancy("Java", "www.hh.ru", this.second);
    }

    @Test
    public void addVacancy() {
     try (DBVacancy vac = new DBVacancy(ConnectionRollback.create(init()))) {
            boolean result = vac.addVacancy(this.junior);
            assertThat(result, is(true));
        } catch (SQLException e) {
         this.LOG.error(e.getMessage(), e);
     }
 }

    @Test
    public void addMultiVac() {
        try (DBVacancy vac = new DBVacancy(ConnectionRollback.create(init()))) {
            List<Vacancy> list = new ArrayList<>();
            list.add(this.junior);
            list.add(this.middle);
            vac.addMultiVac(list);
            assertThat(vac.getAll().size(), is(list.size()));
        } catch (SQLException e) {
            this.LOG.error(e.getMessage(), e);
        }
    }

    @Test
    public void getMaxDate() {
        try (DBVacancy vac = new DBVacancy(ConnectionRollback.create(init()))) {
            vac.addVacancy(this.junior);
            vac.addVacancy(this.middle);
            assertThat(vac.getMaxDate(), is(this.second));
        } catch (SQLException e) {
            this.LOG.error(e.getMessage(), e);
        }
    }
    @Test(expected = Exception.class)
    public void addVacancyException() {
        DBVacancy vac = new DBVacancy();
        boolean result = vac.addVacancy(this.junior);
        assertThat(result, is(true));
    }
}