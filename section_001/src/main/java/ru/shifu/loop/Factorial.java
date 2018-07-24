package ru.shifu.loop;
/**
 * Factorial.
 *
 * @author Pavel Abrikosov (abrikosovp@mail.ru).
 * @version 1.
 * @since 24.06.2018.
 */
public class Factorial {
    /**
     * Расчитать фоктариал.
     * @param n число которое нужно расчитать.
     * @return число - фоктариал.
     */
    public int calc(int n) {
        int result = 1;
        for (int i = result; i <= n; i++) {
            if (n == 0) {
                return 1;
            } else {
                result *= i;
            }
        }
        return result;
    }
}
