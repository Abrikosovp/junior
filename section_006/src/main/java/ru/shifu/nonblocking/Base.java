package ru.shifu.nonblocking;
/**
 * Base.
 *
 * @author Pavel Abrikosov (abrikosovp@mail.ru).
 * @version 1.
 * @since 24.11.2018.
 **/
public class Base {
    private int id;
    private int version;

    public Base(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public int getVersion() {
        return version;
    }
    public void increment() {
        version++;
    }
}
