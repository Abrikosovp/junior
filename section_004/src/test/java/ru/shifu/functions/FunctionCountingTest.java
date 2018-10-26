package ru.shifu.functions;

import org.junit.Test;


import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

import java.util.Arrays;
import java.util.List;
/**
 * FunctionCountingTest.
 *
 * @author Pavel Abrikosov (abrikosovp@mail.ru).
 * @version 1.
 * @since 25.10.2018.
 **/
public class FunctionCountingTest {
    /**
     * Тест - линейная.
     */
    @Test
    public void thenLinear() {
        List<Double> result = new FunctionCounting().diapason(1, 3, aDouble -> 10 + aDouble);
        List<Double> expect = Arrays.asList(11D, 12D, 13D);
        assertThat(expect, is(result));
    }
    /**
     * Тест - квадратичная.
     */
    @Test
    public void thenQuadratic() {
        List<Double> result = new FunctionCounting().diapason(1, 3, aDouble -> Math.pow(aDouble, 2));
        List<Double> expect = Arrays.asList(1D, 4D, 9D);
        assertThat(expect, is(result));
    }
    /**
     * Тест - логарифмическая.
     */
    @Test
    public void thenLogarithmic() {
        List<Double> result = new FunctionCounting().diapason(1, 2, aDouble -> Math.log10(aDouble));
        List<Double> expect = Arrays.asList(0D, 0.3010299956639812D);
        assertThat(expect, is(result));
    }
}
