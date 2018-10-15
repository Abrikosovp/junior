package ru.shifu.list;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;
/**
 * ConvertList2ArrayTest.
 *
 * @author Pavel Abrikosov (abrikosovp@mail.ru).
 * @version 1.
 * @since 14.10.2018.
 **/
public class ConvertList2ArrayTest {
    /**
     * Тест проверяет что мы из ArrayList - получим двумерный массив, а остаток заполняет 0
     */
    @Test
    public void when7ElementsThen9() {
        ConvertList2Array list = new ConvertList2Array();
        int[][] result = list.toArray(
                Arrays.asList(1, 2, 3, 4, 5, 6, 7),
                3
        );
        int[][] expect = {
                {1, 2, 3},
                {4, 5, 6},
                {7, 0, 0}
        };
        assertThat(result, is(expect));
    }
    /**
     * Тест проверяет что мы из ArrayList - получим двумерный массив, а остаток заполняет 0
     */
    @Test
    public void when10ElementsThen12() {
        ConvertList2Array list = new ConvertList2Array();
        ArrayList<Integer> arr = new ArrayList<>();
        arr.add(1);
        arr.add(2);
        arr.add(3);
        arr.add(4);
        arr.add(5);
        arr.add(6);
        arr.add(7);
        arr.add(8);
        arr.add(9);
        arr.add(10);
        int[][] result = list.toArray(arr, 4);
        int[][] execut = {{1, 2, 3}, {4, 5, 6}, {7, 8, 9}, {10, 0, 0}};
        assertThat(execut, is(result));
    }
    /**
     * Тест проверяет что мы из ArrayList - получим двумерный массив, а остаток заполняет 0
     */
    @Test
    public void when4ElementsThen6() {
        ConvertList2Array list = new ConvertList2Array();
        List<Integer> value = Arrays.asList(1, 2, 7);
        int[][] result = list.toArray(value, 2);
        int[][] expect = {
                {1, 2},
                {7, 0}
        };
        assertThat(expect, is(result));
    }
}
