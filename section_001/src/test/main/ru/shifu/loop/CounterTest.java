package ru.shifu.loop;
import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;
/**
 * CounterTest - подсчет суммы чётных чисел в диапазоне.
 *
 * @author Pavel Abrikosov (abrikosovp@mail.ru).
 * @version 1.
 * @since 24.06.2018.
 */
public class CounterTest {
    /**
     * Метод считает сумму четных чисел.
     */
    @Test
    public void whenSumEvenNumbersFromOneToTenThenThirty() {
        Counter counter = new Counter();
        assertThat(counter.add(1, 10), is(30));
    }
}
