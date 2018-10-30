package ru.shifu.iterator;

import java.util.Iterator;
import java.util.NoSuchElementException;
/**
 * EventIterator.
 *
 * @author Pavel Abrikosov (abrikosovp@mail.ru).
 * @version 1.
 * @since 27.10.2018.
 **/
public class EventIterator implements Iterator {
    /**
     * Хранилище
     */
    private final int[] numbers;
    /**
     * Позиция
     */
    private int index = 0;

    public EventIterator(int[] numbers) {
        this.numbers = numbers;
    }

    /**
     * Метод метод пробегает по массиву ,
     * в this.index сохроняет index четных чисел.
     * @return true четное чило, false не четные.
     */
    @Override
    public boolean hasNext() {
        boolean result = false;
        for (int ind = this.index; ind < this.numbers.length; ind++) {
            if (this.numbers[ind] % 2 == 0) {
                this.index = ind;
                result = true;
                break;
            }
        }
        return result;
    }

    /**
     * Метод проверяет что число четное.
     * @return элемент.
     */
    @Override
    public Object next() {
        if (!hasNext()) {
            throw new NoSuchElementException();
        }
        return this.numbers[this.index++];
    }
}
