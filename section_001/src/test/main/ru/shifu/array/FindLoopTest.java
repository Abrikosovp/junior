package ru.shifu.array;
/**
 * SquareTest - Заполнить массив степенями чисел.
 *
 * @author Pavel Abrikosov (abrikosovp@mail.ru).
 * @version 1.
 * @since 25.06.2018.
 **/
import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class FindLoopTest {
    /**
     * Поиск числа в массиве (5 с индексом 0).
     */
    @Test
    public void whenArrayHasLengh5Then0() {
        FindLoop find = new FindLoop();
        int[] input = new int[] {5, 10, 3};
        int value = 5;
        int result = find.indexOf(input, value);
        int expect = 0;
        assertThat(result, is(expect));
    }
    /**
     * Поиск числа в массиве (3 с индексом 2).
     */
    @Test
    public void whenArrayHasLengh3Then2() {
        FindLoop find = new FindLoop();
        int[] input = {5, 10, 3};
        int result = find.indexOf(input, 3);
        int expected = 2;
        assertThat(result, is(expected));
    }
}