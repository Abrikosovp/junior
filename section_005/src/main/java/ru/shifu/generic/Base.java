package ru.shifu.generic;

/**
 * Base.
 *
 * @author Pavel Abrikosov (abrikosovp@mail.ru).
 * @version 1.
 * @since 29.10.2018.
 **/
public abstract class Base {
    /**
     * сохраняет в себе ключ ,
     * по которому мы вычисляем ID.
     */
    private final String id;

    protected Base(final String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }
}
