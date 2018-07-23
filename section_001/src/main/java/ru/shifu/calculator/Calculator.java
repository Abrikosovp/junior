package ru.shifu.calculator;
/**
 * Calculator.
 *
 * @author Pavel Abrikosov (abrikosovp@mail.ru).
 * @version 1.
 * @since 23.06.2018.
 */
public class Calculator {
    private double result;
    /**
     * Сложение чисел.
     * @param first первое.
     * @param second второе.
     */
    public void add(double first, double second) {
        this.result = first + second;
    }
    /**
     * Вычитание чисел.
     * @param first первое.
     * @param second второе.
     */
    public void subtract(double first, double second) {
        this.result = first - second;
    }
    /**
     * Деление чисел.
     * @param first первое.
     * @param second второе.
     */
    public void div(double first, double second) {
        this.result = first / second;
    }

    /**
     * Умножение чисел.
     * @param first первое.
     * @param second второе.
     */
    public void multiple(double first, double second) {
        this.result = first * second;
    }

    public double getResult() {
        return this.result;
    }
}
