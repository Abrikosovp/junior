package ru.shifu.tracker;

import org.junit.Test;

import java.util.*;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class ItemTest {

    @Test
    public void ifVacancyDifferenceThenEqualsShouldBeReturnFalse() {

        Set<Item> first =  new HashSet<>();
        first.add(new Item("test", "test", 1));

        Set<Item>  second = new HashSet<>();
        second.add(new Item("test", "test", 1));

        Set<Item>  second2 = new HashSet<>();
       second2.add(new Item("test", "test", 2));
       second2.add(new Item("test", "test", 2));

        assertThat(first.equals(second), is(true));
        assertThat(second.equals(first), is(true));

        assertThat(first.equals(second2), is(false));
        assertThat(second2.equals(first), is(false));
    }
}