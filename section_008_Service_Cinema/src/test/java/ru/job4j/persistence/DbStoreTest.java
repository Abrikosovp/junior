package ru.job4j.persistence;

import org.junit.Test;
public class DbStoreTest {
    /**
     * Get Halls By Id test.
     *
     * @author Pavel Abrikosov (abrikosovp@mail.ru).
     * @version 1.0.
     * @since 20.02.19
     */
    @Test(expected = IllegalArgumentException.class)
    public void thenGetHallsByIdReturnException() {
        Store store = DbStore.getInstance();
        store.getHallsById(10000);
    }

}