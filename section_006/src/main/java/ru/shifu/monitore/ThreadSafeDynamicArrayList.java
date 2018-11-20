package ru.shifu.monitore;

import net.jcip.annotations.GuardedBy;
import net.jcip.annotations.ThreadSafe;
import ru.shifu.list.DynamicArrayList;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
/**
 * ThreadSafeDynamicArrayList.
 *
 * @author Pavel Abrikosov (abrikosovp@mail.ru).
 * @version 1.
 * @since 21.11.2018.
 **/
@ThreadSafe
public class ThreadSafeDynamicArrayList<E> {

    @GuardedBy("this")
    private DynamicArrayList array;

    public ThreadSafeDynamicArrayList() {
        this.array = new DynamicArrayList();
    }

    public ThreadSafeDynamicArrayList(int capacity) {
        this.array = new DynamicArrayList(capacity);
    }

    /**
     * Метод считает колличество элементов в массиве.
     * @return колличество элементов.
     */
    public synchronized int size() {
       return array.size();
    }

    /**
     * Матод добавляет элементы в массив,
     * и проверяет что массиве есть еще свободное место,
     * если нет , увеличивает массив на 50 %.
     * @param value значение.
     */
    public synchronized void add(E value) {
        this.array.add(value);
    }

    /**
     * Метод дастает элемент по индексу,
     * и проверяет что индекс ввели в нужном диапазоне.
     * @param index индекс элемента.
     * @return значение.
     */
    public synchronized E get(int index) {
        return (E) this.array.get(index);
    }

    /**
     * Метод проверяет что в коллекции элемент присутствует.
     * @param value данные.
     * @return true / false
     */
    public synchronized boolean contains(Object value) {
        return this.array.contains(value);
    }

    /**
     * Метод ищет в коллекции елемент или null.
     * @param value данные.
     * @return индекс элемента если рписутствует , -1 если нет
     */
    public synchronized int indexOf(Object value) {
        return this.array.indexOf(value);
    }

    /**
     * Метод итератор.
     * @return итератор.
     */
    public synchronized Iterator<E> iterator() {
        return copy(this.array).iterator();
    }

    /**
     * Метод создает копию данных - snapshots.
     * @param array оригинал
     * @return копию
     */
    private synchronized List<E> copy(DynamicArrayList array) {
        List<E> list = new ArrayList<>();
        for (Object e: array) {
            list.add((E) e);
        }
        return list;
    }
}
