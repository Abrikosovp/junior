package ru.shifu.list;

import java.util.ArrayList;
import java.util.List;
/**
 * ConvertMatrix2List.
 *
 * @author Pavel Abrikosov (abrikosovp@mail.ru).
 * @version 1.
 * @since 14.10.2018.
 **/
public class ConvertMatrix2List {
    /**
     * Метод конвертирует двумерный массив в ArrayList
     * @param array входной двумерный массив с данными.
     * @return ArrayList.
     */
    public List<Integer> toList(int[][] array) {
        List<Integer> list = new ArrayList<>();
        for (int[] column : array) {
            for (int rows: column) {
                list.add(rows);
            }
        }
        return list;
    }
}
