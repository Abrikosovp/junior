package ru.shifu.store;

import org.junit.Before;
import org.junit.Test;
import java.util.Arrays;
import java.util.List;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;
/**
 * StoreTest.
 *
 * @author Pavel Abrikosov (abrikosovp@mail.ru).
 * @version 1.
 * @since 6.11.2018.
 **/
public class StoreTest {

    Store store = new Store();
    List<Store.User> previous;
    List<Store.User> current;

    @Before
    public void testBefore() {
       previous = Arrays.asList(
                new Store.User(1, "Павел"),
                new Store.User(2, "Карина"),
                new Store.User(3, "Маша"));

       current = Arrays.asList(
                new Store.User(1, "Антон"),
                new Store.User(2, "Карин"),
                new Store.User(3, "Маша"));
    }

    /**
     * Тест редактировали 2 User.
     */
    @Test
    public void whenAdd3ElementsByEdited2Elements() {
        Info info = store.diff(previous, current);
        List<Integer> expect = Arrays.asList(2, 0, 0);
        assertThat(info.listModifications(), is(expect));
    }

    /**
     * Тест Удалили 2 User.
     */
    @Test
    public void whenAdd3ElementsByRemove2Elements() {
        List<Store.User> current = Arrays.asList(new Store.User(1, "Павел"));
        Info info = store.diff(previous, current);
        List<Integer> expect = Arrays.asList(0, 2, 0);
        assertThat(info.listModifications(), is(expect));
    }

    /**
     * Тест add 1 User.
     */
    @Test
    public void whenAdd3ElementsByAdd1Elements() {
        List<Store.User> current = Arrays.asList(
                new Store.User(1, "Павел"),
                new Store.User(2, "Карина"),
                new Store.User(3, "Маша"),
                new Store.User(4, "Иван"));
        Info info = store.diff(previous, current);
        List<Integer> expect = Arrays.asList(0, 0, 1);
        assertThat(info.listModifications(), is(expect));
    }

    /**
     * Тест редактировали 1го User и удалили 1го User
     */
    @Test
    public void whenAdd3ElementsByAdd1ElementsByEdited1Elements() {
        List<Store.User> current = Arrays.asList(
                new Store.User(1, "Кирилл"),
                new Store.User(2, "Карина"),
                new Store.User(3, "Маша"),
                new Store.User(4, "Иван"));
        Info info = store.diff(previous, current);
        List<Integer> expect = Arrays.asList(1, 0, 1);
        assertThat(info.listModifications(), is(expect));
    }
}