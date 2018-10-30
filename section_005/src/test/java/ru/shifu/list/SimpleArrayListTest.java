package ru.shifu.list;
import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;
/**
 * SimpleArrayList.
 *
 * @author Pavel Abrikosov (abrikosovp@mail.ru).
 * @version 1.
 * @since 29.10.2018.
 **/
public class SimpleArrayListTest {

    private SimpleArrayList<Integer> list;

    @Before
    public void beforeTest() {
        list = new SimpleArrayList<>();
        list.add(1000);
        list.add(200);
        list.add(30);
    }

    /**
     * Тест на добавление элемента, и поиск по индексу.
     */
    @Test
    public void henAddThreeElementsThenUseGetOneResultTwo() {
        assertThat(list.get(1), is(200));

    }

    /**
     * Тест проверяет размер списка.
     */
    @Test
    public void whenAddThreeElementsThenUseGetSizeResultThree() {
        assertThat(list.getSize(), is(3));
    }

    /**
     * Тест на удаления элемента из списка.
     */
    @Test
    public void whenAddThreeElementsThenUseDeleteResultTwo() {
        assertThat(list.delete(), is(30));
    }
}
