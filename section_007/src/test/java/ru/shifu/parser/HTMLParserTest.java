package ru.shifu.parser;

import org.junit.Test;
import ru.shifu.tracker.ConnectionRollback;

import java.io.File;
import java.io.IOException;
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
    public void test() throws SQLException, IOException {
            ConnectTest test = new ConnectTest();
            HTMLParser parser = new HTMLParser(ConnectionRollback.create(test.init()));
            new DBVacancy(test.init());
            parser.getPages("http://www.sql.ru/forum/job-offers");
            List<Vacancy> result = parser.parseFile(new File(getClass().getClassLoader().getResource("ParserTestHtml.html").getFile()));
            assertThat(result.size(), is(18));
    }
}