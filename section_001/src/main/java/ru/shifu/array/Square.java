package ru.shifu.array;
/**
 * Square - Заполнить массив степенями чисел.
 *
 * @author Pavel Abrikosov (abrikosovp@mail.ru).
 * @version 1.
 * @since 25.06.2018.
 **/
public class Square {
    /**
     * Заполняет массив числами возведенными в квадрат.
     * @param bound числа.
     * @return заполненный массив.
     */
    public int[] calculate(int bound) {
        int[] rst = new int[bound];
        // заполнить массив через цикл элементами от 1 до bound возведенными в квадрат
        for (int i = 1; i != rst.length + 1; i++) {
            rst[i - 1] = i * i;
        }
        return rst;
    }
}
