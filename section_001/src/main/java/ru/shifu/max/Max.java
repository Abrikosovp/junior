package ru.shifu.max;
/**
 * Max - максимум из двух чисел.
 *
 * @author Pavel Abrikosov (abrikosovp@mail.ru).
 * @version 1.
 * @since 24.06.2018.
 */
public class Max {
    /**
     * Метод - максимум из двух чисел.
     * @param first первое.
     * @param second второе.
     * @return вычисленное значение.
     */
    public int max(int first, int second) {
        return (first > second) ? first : second;
    }
    /**
     * Метод - максимум из трех чисел.
     * @param first первое.
     * @param second второе.
     * @param third третье.
     * @return вычисленное значение.
     */
    public int max(int first, int second, int third) {
        return max(first, max(second, third));
    }
}
