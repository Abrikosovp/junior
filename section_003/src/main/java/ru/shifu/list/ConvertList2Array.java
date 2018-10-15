package ru.shifu.list;

import java.util.List;
import java.util.ListIterator;
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
        int cells = list.size() % rows > 0 ? list.size() / rows + 1 : list.size() / rows ;
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
}
