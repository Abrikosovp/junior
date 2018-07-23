package ru.shifu.calculator;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;
/**
 * Calculate.
 *
 * @author Pavel Abrikosov (abrikosovp@mail.ru).
 * @version 1.
 * @since 23.06.2018.
 */
public class CalculatorTest {
    /**
     * Сложение чисел.
     */
    @Test
    public void whenAddOnePlusOneThenTwo() {
        Calculator calc = new Calculator();
        calc.add(1D, 1D);
        double result = calc.getResult();
        double expected = 2D;
        assertThat(result, is(expected));
    }
    /**
     * Вычитание чисел.
     */
    @Test
    public void whenSubtractOnePlusOneThenTwo() {
        Calculator calc = new Calculator();
        calc.subtract(10D, 1D);
        double result = calc.getResult();
        double expected = 9D;
        assertThat(result, is(expected));
    }
    /**
     * Умножение чисел.
     */
    @Test
    public void whenDivOnePlusOneThenTwo() {
        Calculator calcul = new Calculator();
        calcul.multiple(4D, 4D);
        double result = calcul.getResult();
        assertThat(result, is(16D));
    }
    /**
     * Деление чисел.
     */
    @Test
    public void whenMultipleOnePlusOneThenTwo() {
        Calculator calc = new Calculator();
        calc.div(10D, 5D);
        double result = calc.getResult();
        assertThat(result, is(2D));

    }
    }
