package ru.shifu.list;

import org.junit.Test;
import java.util.Arrays;
import java.util.List;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;
/**
 * ConvertMatrix2ListTest.
 *
 * @author Pavel Abrikosov (abrikosovp@mail.ru).
 * @version 1.
 * @since 14.10.2018.
 **/
public class ConvertMatrix2ListTest {
    /**
     * Тест проверяет что метод конвертирует двумерный массив в ArrayList
     */
    @Test
    public void when2on2ArrayThenList4() {
        ConvertMatrix2List matrix = new ConvertMatrix2List();
        int[][] arr = {{1, 2, 3}, {4, 5, 6}};
        List<Integer> result = matrix.toList(arr);
        List<Integer> expect = Arrays.asList(1, 2, 3, 4, 5, 6);
        assertThat(expect, is(result));
    }
}
