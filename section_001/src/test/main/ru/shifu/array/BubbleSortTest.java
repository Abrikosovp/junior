package ru.shifu.array;
/**
 * BubbleSortTest программа для сортировки массива методом перестановки.
 *
 * @author Pavel Abrikosov (abrikosovp@mail.ru).
 * @version 1.
 * @since 26.06.2018.
 **/
import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class BubbleSortTest {
    /**
     * Метод сортировки Пузыриком.
     */
    @Test
    public void whenSortArrayWithTenElementsThenSortedArray() {
        //напишите здесь тест, проверяющий сортировку массива из 10 элементов методом пузырька, например {1, 5, 4, 2, 3, 1, 7, 8, 0, 5}.
        BubbleSort bubble = new BubbleSort();
        int[] input = {1, 5, 4, 2, 3, 1, 7, 8, 0, 5};
        int[] result = bubble.sort(input);
        int[] expected = {0, 1, 1, 2, 3, 4, 5, 5, 7, 8};
        assertThat(result, is(expected));
    }
}