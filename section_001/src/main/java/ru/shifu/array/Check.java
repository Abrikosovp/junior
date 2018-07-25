package ru.shifu.array;
/**
 * Check .
 *
 * @author Pavel Abrikosov (abrikosovp@mail.ru).
 * @version 1.
 * @since 25.06.2018.
 **/
public class Check {
    /**
     * Метод должен проверить, что все элементы в массиве являются true или false.
     * @param data массив true или false.
     * @return результат.
     */
    public boolean mono(boolean[] data) {
        boolean result = false;
        for (int index = 1; index < data.length; index++) {
            if (data[0] == data[index]) {
                result = true;
            } else {
                result = false;
                break;
            }
        }
        return result;
    }
}
