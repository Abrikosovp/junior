package ru.shifu.array;
/**
 * FindLoop - Классический поиск перебором.
 *
 * @author Pavel Abrikosov (abrikosovp@mail.ru).
 * @version 1.
 * @since 25.06.2018.
 **/
public class FindLoop {
    /**
     * Поиск числа в массиве.
     * @param data массив с числами.
     * @param el число которое нужно найти.
     * @return индекс этого числа.
     */
    public int indexOf(int[] data, int el) {
        int rst = -1; // если элемента нет в массиве, то возвращаем -1.
        for (int index = 0; index < data.length; index++) {
            if (data[index] == el) {
                rst = index;
                break;
            }
        }
        return rst;
    }
}
