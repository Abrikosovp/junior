package ru.shifu.list;
/**
 * SimpleContainer.
 *
 * @author Pavel Abrikosov (abrikosovp@mail.ru).
 * @version 1.
 * @since 30.10.2018.
 **/
public interface SimpleContainer<E> extends Iterable<E> {
    void add(E value);
    E get(int index);
}
