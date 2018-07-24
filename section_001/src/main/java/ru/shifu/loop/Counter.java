package ru.shifu.loop;
/**
 * Counter - подсчет суммы чётных чисел в диапазоне.
 *
 * @author Pavel Abrikosov (abrikosovp@mail.ru).
 * @version 1.
 * @since 24.06.2018.
 */
public class Counter {
    /**
     * Метод считает сумму четных чисел.
     * @param start начальное число.
     * @param finish конечное число.
     * @return сумму чисел.
     */
    public int add(int start, int finish) {
        int result = 0;
        for (int i = start; i <= finish; i++) {
            if (i % 2 == 0) {
                result +=  i;
            }
        }
        return result;
    }
}
