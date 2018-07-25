package ru.shifu.array;
/**
 * Turn.
 *
 * @author Pavel Abrikosov (abrikosovp@mail.ru).
 * @version 1.
 * @since 25.06.2018.
 **/
public class Turn {
    /**
     * Метод переварачивает массив задом наперёд
     * @param array массив.
     * @return перевернутый массив.
     */
    public int[] turn(int[] array) {
        for (int index = 0; index < array.length / 2; index++) {
            int tmp = array[index];
            array[index] = array[array.length - index - 1];
            array[array.length - index - 1] = tmp;
        }
        return array;
    }
}
