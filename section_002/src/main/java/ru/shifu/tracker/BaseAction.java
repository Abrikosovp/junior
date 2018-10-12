package ru.shifu.tracker;
/**
 * abstract class BaseAction - используем Template method для Menu Tracker.
 *
 * @author Pavel Abrikosov (abrikosovp@mail.ru).
 * @version 1.
 * @since 12.10.2018.
 **/
public abstract class BaseAction implements UserAction {
    /**
     * Имя действия.
     */
    private final String name;
    /**
     * Уникальный ключ(номер действия)
     */
    private final int key;

    public BaseAction(String name, int key) {
        this.name = name;
        this.key = key;
    }

    /**
     * Ключ запуска для добавления.
     * @return key.
     */
    @Override
    public int key() {
        return key;
    }

    /**
     * Выводит описывающую информацию.
     * @return key, name.
     */
    @Override
    public String info() {
        return String.format("%s. %s", this.key(), this.name);
    }
}
