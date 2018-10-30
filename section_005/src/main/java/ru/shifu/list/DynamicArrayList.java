package ru.shifu.list;
import java.util.*;

/**
 * DynamicArrayList.
 *
 * @author Pavel Abrikosov (abrikosovp@mail.ru).
 * @version 1.
 * @since 30.10.2018.
 **/
public class DynamicArrayList<T> implements SimpleContainer<T> {
    /**
     * Хранилище
     */
    private Object[] container;
    /**
     * Размер массива по умолчанию
     */
    private static final int DEFAULT_CAPACITY = 10;
    /**
     * Счетчик колличества добавленных элементов в массив
     */
    private int elementsCount;
    /**
     * Каждая операция, которая структурно модифицирует
     * коллекцию должна инкрементировать этот счетчик.
     */
    private transient int modCount = 0;

    /**
     * Устанавливаем размер динамического массива.
     */
    public DynamicArrayList() {
        this.container = new Object[this.DEFAULT_CAPACITY];
    }
    /**
     * Устанавливаем размер динамического массива.
     */
    public DynamicArrayList(int capacity) {
        if (capacity == 0) {
            this.container = new Object[this.DEFAULT_CAPACITY];
        } else if (capacity > 0) {
            this.container = new Object[capacity];
        } else {
            throw new IllegalArgumentException("Illegal capacity" + capacity);
        }
    }

    /**
     * Метод считает колличество элементов в массиве.
     * @return колличество элементов.
     */
    public int size() {
        return this.elementsCount;
    }

    /**
     * Матод добавляет элементы в массив,
     * и проверяет что массиве есть еще свободное место,
     * если нет , увеличивает массив на 50 %.
     * ++ счетчик.
     * @param value значение.
     */
    @Override
    public void add(T value) {
        checkBandwidth(this.elementsCount + 1);
        this.container[this.elementsCount++] = value;
        modCount++;
    }

    /**
     * Метод дастает элемент по индексу,
     * и проверяет что индекс ввели в нужном диапазоне.
     * @param index индекс элемента.
     * @return значение.
     */
    @Override
    public T get(int index) {
        rangeCheck(index);
        return (T) this.container[index];
    }

    /**
     * Метод проверяет что индекс ввели в нужном диапазоне.
     * @param index индекс элемента.
     */
    private void rangeCheck(int index) {
        if (index > elementsCount) {
            throw new IndexOutOfBoundsException(String.format("Index %s size %s", index, this.elementsCount));
        }
    }

    /**
     * Проверяет пропускную способность.
     * @param minCapacity колличество элементов в массиве.
     */
    private void checkBandwidth(int minCapacity) {
        if (minCapacity - this.container.length > 0) {
            toIncrease();
        }
    }

    /**
     * Метод увеличивает массив на 50%
     */
    private void toIncrease() {
        int oldCapacity = this.container.length;
        int newCapacity = oldCapacity + (oldCapacity >> 1);
        this.container = Arrays.copyOf(this.container, newCapacity);
    }

    /**
     * Метод создает новый итератор.
     * @return new Iterator.
     */
    @Override
    public Iterator<T> iterator() {
        return new IteratorIt();
    }

    private class IteratorIt implements Iterator<T> {
        /**
         * внутренний счетчик.
         */
        private int iter = 0;
        /**
         * Итератор запоминает значение modCount счетчика на момент своего создания,
         * а затем на каждой итерации сравнивает сохраненное значение,
         * с текущим значением поля modCount, если они отличаются, то генерируется исключение.
         */
        private int expectedModCount = modCount;

        /**
         * Метод проверяет есть ли в следующей ячейки элемент и
         * если с момента создания итератора коллекция подверглась структурному изменению,
         * итератор должен кидать ConcurrentModificationException.
         * @return true / false.
         */
        @Override
        public boolean hasNext() {
            if (expectedModCount != modCount) {
                throw new ConcurrentModificationException();
            }
            return this.iter < elementsCount;
        }

        /**
         * Метод который переставляет флаг.
         * @return значение ячейки
         */
        @Override
        public T next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }

            return (T) container[iter++];
        }
    }
}