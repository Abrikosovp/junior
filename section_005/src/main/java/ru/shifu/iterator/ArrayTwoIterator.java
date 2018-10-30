package ru.shifu.iterator;


import java.util.Iterator;
import java.util.NoSuchElementException;
/**
 * ArrayTwoIterator.
 *
 * @author Pavel Abrikosov (abrikosovp@mail.ru).
 * @version 1.
 * @since 27.10.2018.
 **/
public class ArrayTwoIterator implements Iterator {
    /**
     * хранилище
     */
    private final int[][] value;
    /**
     * отвечает за столбец в arr[][]
     */
    private int of = 0;
    /**
     * отвечает за строку в arr[][]
     */
    private int at = 0;

    public ArrayTwoIterator(int[][] value) {
        this.value = value;
    }

    /**
     * Метод проверяет что :
     * Arr.length  == колличеству строк.
     * Позиция в строке не привышает размер строки.
     * Что в array[][] есть следующая строка.
     * @return true если условие верно, false если нет
     */
    @Override
    public boolean hasNext() {
        return (this.of < this.value.length && this.at < this.value[this.of].length)
                || (this.of + 1) < this.value.length;
    }

    /**
     * Метод совершает:
     * Передвижения по строке ,и запоминает индекс следующей ячейки.
     * Затем переходит на новую строку.
     * @return элемент
     */
    @Override
    public Object next() {
        Integer result = null;

        if (this.of >= this.value.length) {
            throw new NoSuchElementException();
        }

        if (this.at < this.value[this.of].length) {
            result = this.value[this.of][this.at++];
        } else {
            this.of++;
            this.at = 0;
            result = this.value[of][at++];
        }
        return result;
    }
}
