package ru.shifu.array;

import java.util.Arrays;

/**
 * ArrayDuplicate удаление дубликатов в массиве.
 *
 * @author Pavel Abrikosov (abrikosovp@mail.ru).
 * @version 1.
 * @since 26.06.2018.
 **/
public class ArrayDuplicate {
    /**
     * Метод удаляет дубликаты в массиве.
     * @param array Strung[] array.
     * @return Strung[] array - дубликаты.
     */
    String[] remove(String[] array) {
        int value = array.length;
        for (int row = 0; row < value; row++) {
            for (int in = row + 1; in < value; in++) {
                if (array[row].equals(array[in])) {
                    array[in] = array[value - 1];
                    value--;
                    in--;
                }
            }
        }
        return Arrays.copyOf(array, value);
}
}

