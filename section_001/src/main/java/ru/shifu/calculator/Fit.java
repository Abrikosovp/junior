package ru.shifu.calculator;
/**
 * Fit.
 *
 * @author Pavel Abrikosov (abrikosovp@mail.ru).
 * @version 1.
 * @since 24.06.2018.
 */
public class Fit {
    public static final int MAN = 100;
    public static final int WOMAN = 110;
    public static final double COEFFICIENT = 1.15D;
    /**
     * Идеальный вес для мужщины.
     * @param height Рост.
     * @return идеальный вес.
     */
    public double manWeight(double height) {
        return (height - MAN) * COEFFICIENT;
    }
    /**
     * Идеальный вес для женщины.
     * @param height Рост.
     * @return идеальный вес.
     */
    public double womanWeight(double height) {
        return (height - WOMAN) * COEFFICIENT;
    }
}
