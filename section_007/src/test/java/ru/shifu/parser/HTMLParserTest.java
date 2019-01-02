package ru.shifu.parser;

import org.junit.Test;
import ru.shifu.tracker.ConnectionRollback;

import java.sql.SQLException;
import java.util.List;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;
/**
 * DBVacancyTest
 * @author Pavel Abrikosov(abrikosovp@mail.ru)
 * @version 0.1$
 * @since 0.1
 * 02.01.2019
 */
public class HTMLParserTest {

    @Test
    public void test() throws SQLException {
            ConnectTest test = new ConnectTest();
            HTMLParser parser = new HTMLParser(ConnectionRollback.create(test.init()));
            DBVacancy dbVacancy = new DBVacancy(test.init());
            parser.parse();

            List<Vacancy> list = dbVacancy.getAll();
            assertThat(list.size(), is(0));
    }
}