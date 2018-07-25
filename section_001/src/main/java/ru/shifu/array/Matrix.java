package ru.shifu.array;
/**
 * Matrix Двухмерный массив. Таблица умножения.
 *
 * @author Pavel Abrikosov (abrikosovp@mail.ru).
 * @version 1.
 * @since 26.06.2018.
 **/
public class Matrix {
    /**
     * Метод рисующий матрицу с таблицей умнажения.
     * @param size размер матрицы.
     * @return матрица(таблица умножения).
     */
    int[][] multiple(int size) {
        int[][] table = new int[size][size];
        for (int row = 0; row < table.length; row++) {
            for (int colum = 0; colum < table.length; colum++) {
                table[row][colum] = (row + 1) * (colum + 1);
            }
        }
        return table;
    }
}
