package ru.shifu.comparator;

import java.util.Comparator;
/**
 * ListCompare.
 *
 * @author Pavel Abrikosov (abrikosovp@mail.ru).
 * @version 1.
 * @since 14.10.2018.
 **/
public class ListCompare implements Comparator<String> {
    /**
     * Метод сравнивает два слова.
     * @param left первое слово.
     * @param right второе слово.
     * @return 0 - если они равны.
     */
    @Override
    public int compare(String left, String right) {
        int value = Math.min(left.length(), right.length());
        int result = 0;
        for (int i = 0; i < value && result == 0; i++) {
            result = Character.compare(left.charAt(i), right.charAt(i));
        }
        if (result == 0) {
            result = Integer.compare(left.length(), right.length());
        }
        return result;
    }
}
