package ru.shifu.generic;
import ru.shifu.generic.exception.FullArrayException;
import ru.shifu.generic.exception.NoItemsException;

/**
 * Store.
 *
 * @author Pavel Abrikosov (abrikosovp@mail.ru).
 * @version 1.
 * @since 29.10.2018.
 **/
public interface Store<T extends Base> {
    void add(T model) throws FullArrayException;

    boolean replace(String id, T model);

    boolean delete(String id) throws NoItemsException;

    T findById(String id);
}
