package ru.shifu.array;
/**
 * ArrayDuplicateTest удаление дубликатов в массиве.
 *
 * @author Pavel Abrikosov (abrikosovp@mail.ru).
 * @version 1.
 * @since 26.06.2018.
 **/
import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;
public class ArrayDuplicateTest {
    /**
     * Метод удаляет дубликаты в массиве.
     * {"Привет", "Мир", "Привет", "Супер", "Мир"} должно получиться {"Привет", "Мир", "Супер"}
     */
    @Test
    public void whenRemoveDuplicatesThenArrayWithoutDuplicate() {
        //напишите здесь тест, проверяющий удаление дубликатов строк из массива строк.
        ArrayDuplicate array = new ArrayDuplicate();
        String[] input = {"Привет", "Мир", "Привет", "Супер", "Мир"};
        String[] expected = {"Привет", "Мир", "Супер"};
        String[] result = array.remove(input);
        assertThat(result, is(expected));
    }
}