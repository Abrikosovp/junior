package ru.shifu.servlets;

import org.junit.Test;
import ru.shifu.userstorage.models.PersonalData;
import ru.shifu.userstorage.models.Role;
import ru.shifu.userstorage.models.User;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;

/**
 * PersonTest.
 *
 * @author Pavel Abrikosov (abrikosovp@mail.ru)
 * @version 0.5$
 * @since 0.1
 * 11.02.2019
 */
public class PersonTest {

    @Test
    public void whenHashCodeThenResult() {
      Person personFirst = new Person("first", "second", "sex", "desc");
      Person personSecond = new Person("first", "second", "sex", "desc");
      long a = personFirst.hashCode();
      long b = personSecond.hashCode();
      assertThat(a, is(b));
    }

    @Test
    public void whenEqualsThenResult() {
        Person personFirst = new Person("first", "second", "sex", "desc");
        Person personSecond = new Person("first", "second", "sex", "desc");
        User user = new User("1", "1", "1", Role.ADMIN, new PersonalData("Pavel", "abriksovp@mail.ru", "Russia", "Moscow"));
        boolean userFalse = personFirst.equals(user);

        assertTrue(personFirst.getSex().equals(personSecond.getSex()));
        assertTrue(personFirst.getDescription().equals(personFirst.getDescription()));
        assertTrue(personFirst.getDescription().equals(personSecond.getDescription()));
        assertTrue(personFirst.equals(personFirst));
        assertFalse(userFalse);
    }

    @Test
    public void whenToStringThenResult() {
        Person personFirst = new Person("first", "second", "sex", "desc");
        Person personSecond = new Person("first", "second", "sex", "desc");
        assertThat(personFirst.toString(), is(personSecond.toString()));
        assertThat(personFirst.getDescription(), is("desc"));
        assertThat(personFirst.getSecondname(), is("second"));
    }
}