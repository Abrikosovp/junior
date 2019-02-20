package ru.job4j.model;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

/**
 * Hall test
 *
 * @author Pavel Abrikosov (abrikosovp@mail.ru).
 * @version 1.0.
 * @since 20.02.19
 */
public class HallTest {

    @Test
    public void whenCreateHallThenGetParam() {
        Hall hall = new Hall(23, 2, 3, 500, false);
        assertThat(hall.getId(), is(23));
        assertThat(hall.getRow(), is(2));
        assertThat(hall.getSeat(), is(3));
        assertThat(hall.isBooked(), is(false));
        assertThat(hall.getPrice(), is(500));
    }

    @Test
    public void whenComparingTwoObjectsHall() {
        Hall hall = new Hall(23, 2, 3, 500, false);
        Hall theSame = new Hall(23, 2, 3, 500, false);
        assertTrue(hall.equals(theSame));
        assertFalse(hall.equals(new Accounts(15, "name", "159")));
        assertTrue(hall.equals(hall));
        assertFalse(hall.equals(null));
    }

    @Test
    public void whenGetToStringHall() {
        Hall hall = new Hall(23, 2, 3, 500, false);
        String result = String.format(
                "Hall{id=%s, row=%s, seat=%s, price=%s, booked=%s}",
                23, 2, 3, 500, false);
        assertThat(hall.toString(), is(result));
    }
}