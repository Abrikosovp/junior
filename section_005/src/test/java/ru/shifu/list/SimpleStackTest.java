package ru.shifu.list;

import org.hamcrest.core.Is;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
/**
 * SimpleStackTest.
 *
 * @author Pavel Abrikosov (abrikosovp@mail.ru).
 * @version 1.
 * @since 31.10.2018.
 **/
public class SimpleStackTest {

    SimpleStack<String> names = new SimpleStack<>();

    @Before
    public void setup() {
        names.push("Павел");
        names.push("Настя");
        names.push("Виталий");
    }

    @Test
    public void whenTestPollMethod() {
        Assert.assertThat(names.poll(), Is.is("Виталий"));
        Assert.assertThat(names.poll(), Is.is("Настя"));
        Assert.assertThat(names.poll(), Is.is("Павел"));
    }

    @Test
    public void whenStackEmptyThenReturnTrue() {
        names = new SimpleStack<>();
        Assert.assertThat(names.isEmpty(), Is.is(true));
    }
    @Test
    public void whenStackEmptyThenReturnFalse() {
        names = new SimpleStack<>();
        names.push("Федор");
        Assert.assertThat(names.isEmpty(), Is.is(false));
    }
}