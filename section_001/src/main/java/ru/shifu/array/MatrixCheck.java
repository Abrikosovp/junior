package ru.shifu.array;
/**
 * MatrixCheck - Квадратный массив заполнен true или false по диагоналям
 *
 * @author Pavel Abrikosov (abrikosovp@mail.ru).
 * @version 1.
 * @since 26.06.2018.
 **/
public class MatrixCheck {
    /**
     * Метод проверяет, что все элементы по диагоналям равны true или false.
     * (проверяет по диагонали что 1вый элемент == следующему элементу идущему по диагонали.)
     * @param data массив заполненный true или false.
     * @return true или false.
     */
    public boolean mono(boolean[][] data) {
        boolean result = true;
        for (int j = 0; j < data.length - 1; j++) {
            if (data[j][j] != data[j + 1][j + 1] || data[j][data.length - 1 - j] != data[j + 1][data.length - 2 - j]) {
                result = false;
                break;
            }
        }
        return result;
    }
}