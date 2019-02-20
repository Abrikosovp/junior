package ru.job4j.model;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

/**
 * Account test.
 *
 * @author Pavel Abrikosov (abrikosovp@mail.ru).
 * @version 1.0.
 * @since 20.02.19
 */
public class AccountsTest {

    @Test
    public void whenCreateAccountThenGetParam() {
        Accounts accounts = new Accounts(23,
                "Petrov Alex Nikolaevich", "+7(111)2223344");
        assertThat(accounts.getId(), is(23));
        assertThat(accounts.getUsername(), is("Petrov Alex Nikolaevich"));
        assertThat(accounts.getPhone(), is("+7(111)2223344"));

    }

    @Test
    public void whenComparingTwoObjectsHall() {
        Accounts accounts = new Accounts(23,
                "Petrov Alex Nikolaevich", "+7(111)2223344");
        Accounts theSame = new Accounts(23,
                "Petrov Alex Nikolaevich", "+7(111)2223344");
        Accounts other = new Accounts();
        assertTrue(accounts.equals(theSame));
        assertFalse(accounts.equals(new Hall(15, 12, 12, 12, true)));
        assertTrue(accounts.equals(accounts));
        assertFalse(accounts.equals(null));
    }

    @Test
    public void whenGetToStringHall() {
        Accounts accounts = new Accounts(23,
                "Petrov Alex Nikolaevich", "+7(111)2223344");
        String result = String.format(
                "Accounts{id=%s, username=%s, phone=%s}",
                23, "Petrov Alex Nikolaevich", "+7(111)2223344");
        assertThat(accounts.toString(), is(result));
    }
}