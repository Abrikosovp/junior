package ru.shifu.generic;

import ru.shifu.generic.exception.FullArrayException;
import ru.shifu.generic.exception.NoItemsException;

import java.util.Iterator;

/**
 * SimpleArray.
 *
 * @author Pavel Abrikosov (abrikosovp@mail.ru).
 * @version 1.
 * @since 27.10.2018.
 **/
public class SimpleArray<T> implements Iterable<T> {
    /**
     * Хранилище
     */
    private final Object[] array;
    /**
     * Колличество элементов в массиве
     */
    private int elementsCount = 0;

    /**
     * устанавливаем размер хранилища
     * @param index
     */
    public SimpleArray(int index) {
        this.array = new Object[index];
    }

    /**
     * Метод добавляет элемент в масиив.
     * @param model элемент.
     * @throws FullArrayException массив полный.
     */
    public void add(T model) throws FullArrayException {
        if (this.elementsCount >= this.array.length) {
            throw new FullArrayException("Full Array");
        }
        this.array[this.elementsCount++] = model;
    }

    /**
     * Устанавливает элемент(перезаписывает)
     * @param index индекс куда установить.
     * @param model эл который нужно установить.
     */
    public void set(int index, T model) {
        this.array[index] = model;
    }

    /**
     * Удаляет элемент.
     * Работает по принципу:
     * смещает все элементы на 1 в сторону удаляймого элемента ,
     * и в последний элемент записывает null.
     * @param index ячейки которую нужно удалить.
     */
    public void delete(int index) throws NoItemsException {
        if (this.elementsCount == 0) {
            throw new NoItemsException("No items");
        } else {
            this.elementsCount--;
        }
        System.arraycopy(array, index + 1, array, index, array.length - index - 1);
    }

    /**
     * Медод получить элемент по индексу.
     * @param index получаймого элемента.
     * @return элемент.
     */
    public T get(int index) {
        return (T) this.array[index];
    }

    /**
     * Метод размер хранилища.
     * @return length.
     */
    public int size() {
        return this.array.length;
    }

    @Override
    public Iterator<T> iterator() {
        return new Iterator<T>() {
            private int posIter = 0;

            @Override
            public boolean hasNext() {
                return this.posIter < elementsCount;
            }

            @Override
            public T next() {
                return (T) array[posIter++];
            }
        };
    }
}
