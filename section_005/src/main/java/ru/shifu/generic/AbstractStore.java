package ru.shifu.generic;


import ru.shifu.generic.exception.FullArrayException;
import ru.shifu.generic.exception.NoItemsException;

/**
 * AbstractStore.
 *
 * @author Pavel Abrikosov (abrikosovp@mail.ru).
 * @version 1.
 * @since 29.10.2018.
 **/
public class AbstractStore<T extends Base> implements Store<T> {
    /**
     * Хранилище
     */
    private final SimpleArray<T> store;

    /**
     * устанавливаем размер хранилища
     * @param index index.
     */
    public AbstractStore(int index) {
        this.store = new SimpleArray<>(index);
    }

    /**
     * Метод добавляет элемент в массив.
     * @param model модель.
     * @throws FullArrayException массив полный.
     */
    @Override
    public void add(T model) throws FullArrayException {
        this.store.add(model);
    }

    /**
     * Метод заменяет элемент в массиве.
     * @param id id элемента.
     * @param model модель.
     * @return true / false.
     */
    @Override
    public boolean replace(String id, T model) {
        boolean result = false;
        int value = getIndex(id);
        if (value != -1) {
            this.store.set(value, model);
            result = true;
        }
        return result;
    }

    /**
     * Метод удаления элемент в массиве.
     * @param id id элемента.
     * @return true / false.
     * @throws NoItemsException нет элемента в массиве.
     */
    @Override
    public boolean delete(String id) throws NoItemsException {
        boolean result = false;
        int value = this.getIndex(id);
        if (value != -1) {
            this.store.delete(value);
            result = true;
        }
        return result;
    }

    /**
     * Поиск элемента по ID.
     * @param id id елемента.
     * @return елемент.
     */
    @Override
    public T findById(String id) {
        int index = this.getIndex(id);
        return index != -1 ? this.store.get(index) : null;
    }

    /**
     *
     * Метод достает индекс.
     * Работает таким спосабом:
     * метод ищет в массиве по ключу которому передали,
     * и возвращает индекс ячейки массива.
     * @param id ключ.
     * @return ячейку в массиве.
     */
    private int getIndex(String id) {
        int result = -1;
        for (int index = 0; index < this.store.size(); index++) {
            T value = this.store.get(index);
            if (value != null && id.equals(value.getId())) {
                result = index;
            }
        }
        return result;
    }
}
