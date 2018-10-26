package ru.shifu.list;

import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;
import java.util.Spliterator;

/**
 * ConvertList2Array.
 *
 * @author Pavel Abrikosov (abrikosovp@mail.ru).
 * @version 1.
 * @since 14.10.2018.
 **/
public class ConvertList2Array {
    /**
     * Метод конвертирует из ArrayList в двумерный массив, пустые ячейки заполняет 0.
     * @param list коллекция с элементами
     * @param rows колличество строк
     * @return двумерный массив.
     */
    public int[][] toArray(List<Integer> list, int rows) {
        int cells = list.size() % rows > 0 ? list.size() / rows + 1 : list.size() / rows;
        int[][] array = new int[rows][cells];
        ListIterator<Integer> iterator = list.listIterator();
        for (int[] row : array) {
            for (int index = 0; index < row.length; index++) {
                if (iterator.hasNext()) {
                 row[index] = iterator.next();
                }
            }
        }
        return array;
    }

    /**
     * Метод конвертирует ListArrays в ListInteger
     * @param list arrays.
     * @return list Integer
     */
    public List<Integer> convertList(List<int[]> list) {
        List<Integer> arr = new ArrayList<>();
        for (int[] column : list) {
            for (int row : column) {
                arr.add(row);
            }
        }
        return arr;
    }
}
