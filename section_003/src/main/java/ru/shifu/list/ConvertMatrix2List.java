package ru.shifu.list;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

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
        return Arrays.stream(array)
                .flatMapToInt(Arrays::stream)
                .boxed()
                .collect(Collectors.toCollection(ArrayList::new));
    }
}
