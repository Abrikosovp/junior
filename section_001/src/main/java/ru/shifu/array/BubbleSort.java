package ru.shifu.array;
/**
 * BubbleSort программа для сортировки массива методом перестановки.
 *
 * @author Pavel Abrikosov (abrikosovp@mail.ru).
 * @version 1.
 * @since 26.06.2018.
 **/
public class BubbleSort {
    /**
     * Метод сортировки Пузыриком.
     * @param array массив который требует отсортировки.
     * @return отсортированный массив.
     */
    public int[] sort(int[] array) {
        for (int index = 0; index < array.length; index++) {
            for (int barrer = 0; barrer < array.length - 1; barrer++) {
                if (array[barrer] > array[barrer + 1]) {
                    int tmp = array[barrer];
                    array[barrer] = array[barrer + 1];
                    array[barrer + 1] = tmp;
                }
            }
        }
        return array;
    }
}
