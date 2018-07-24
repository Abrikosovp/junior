package ru.shifu.loop;
import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;
/**
 * FactorialTest
 *
 * @author Pavel Abrikosov (abrikosovp@mail.ru).
 * @version 1.
 * @since 24.06.2018.
 */
public class FactorialTest {
    /**
     * Фоктариал числа 5 == 120
     */
    @Test
    public void whenCalculateFactorialForFiveThenOneHundreedTwenty() {
        //напишите здесь тест, проверяющий, что факториал для числа 5 равен 120.
        Factorial fact = new Factorial();
        assertThat(fact.calc(5), is(120));
    }
    /**
     * Фоктариал числа 0 == 1
     */
    @Test
    public void whenCalculateFactorialForZeroThenOne() {
        //напишите здесь тест, проверяющий, что факториал для числа 0 равен 1.
        Factorial fact = new Factorial();
        assertThat(fact.calc(0), is(1));
    }
}
