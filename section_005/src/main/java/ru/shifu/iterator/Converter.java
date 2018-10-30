package ru.shifu.iterator;

import java.util.Iterator;
import java.util.NoSuchElementException;
/**
 * Converter.
 *
 * @author Pavel Abrikosov (abrikosovp@mail.ru).
 * @version 1.
 * @since 27.10.2018.
 **/
public class Converter {
    Iterator<Integer> iIterator;

    /**
     * Итератор, пробегает по вложенными итераторам без копирования данных.
     * @param iterator объект итератор итератор
     * @return Итератор чисел.
     */
    Iterator<Integer> convert(Iterator<Iterator<Integer>> iterator) {

        /**
         * Передает ссылку на следующий вложенный Iterator.
         */
        iIterator = iterator.next();

        return new Iterator<Integer>() {

            /**
             * Метод пробегает по всем вложенным итераторам и проверят:
             * что в каждом итераторе есть следующий элемент.
             * что есть следующий вложенный итератор.
             * @return true если есть следующий эле, и след Iterator , false если нет.
             */
            @Override
            public boolean hasNext() {

                while (!iIterator.hasNext() && iterator.hasNext()) {
                    iIterator = iterator.next();
                }
                return iIterator.hasNext();
            }

            /**
             * Метод перекидывает указатель.
             * @return значение.
             */
            @Override
            public Integer next() {

                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                return iIterator.next();
            }
        };
    }
}

