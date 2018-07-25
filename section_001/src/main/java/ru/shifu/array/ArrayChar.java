package ru.shifu.array;
/**
 * ArrayChar .
 *
 * @author Pavel Abrikosov (abrikosovp@mail.ru).
 * @version 1.
 * @since 25.06.2018.
 **/
public class ArrayChar {
    private char[] data;
    /**
     * ArrayChar - конструктор.
     * @param line оригинальное слово.
     */
    public ArrayChar(String line) {
        this.data = line.toCharArray();
    }
    /**
     * startWith Этот метод проверяет, что слова начинается в определенной последовательности.
     * @param prefix внутреннее слово.
     * @return result.
     */
    public boolean startWith(String prefix) {
        boolean result = true;
        char[] value = prefix.toCharArray();
        for (int i = 0; i < value.length; i++) {
            if (data[i] != value[i]) {
                result = false;
                break;
            }
        }

        return result;
    }

}
