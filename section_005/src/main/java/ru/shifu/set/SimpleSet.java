package ru.shifu.set;

import ru.shifu.list.DynamicArrayList;
import java.util.Iterator;
/**
 * SimpleSet.
 *
 * @author Pavel Abrikosov (abrikosovp@mail.ru).
 * @version 1.
 * @since 1.11.2018.
 **/
public class SimpleSet<E> implements Iterable<E> {

    DynamicArrayList array;

    public SimpleSet() {
        array = new DynamicArrayList();
    }

    /**
     * Метод добавляет данные в Set,
     * но дубликаты не добавляются.
     * @param value данные
     */
    void add(E value) {
        if (!this.array.contains(value)) {
            this.array.add(value);
        }

    }

    /**
     * Метод размер Set.
     * @return size.
     */
    public int size() {
        return this.array.size();
    }

    /**
     * Итератор Set.
     * @return iterator.
     */
    @Override
    public Iterator<E> iterator() {
        return this.array.iterator();
    }
}
