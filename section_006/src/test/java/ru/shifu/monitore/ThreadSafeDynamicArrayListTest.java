package ru.shifu.monitore;

import org.junit.Before;
import org.junit.Test;
import java.util.Iterator;
import java.util.NoSuchElementException;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;
/**
 * ThreadSafeDynamicArrayListTest.
 *
 * @author Pavel Abrikosov (abrikosovp@mail.ru).
 * @version 1.
 * @since 21.11.2018.
 **/
public class ThreadSafeDynamicArrayListTest {
    private ThreadSafeDynamicArrayList<Integer> list;

    @Before
    public void beforeTest() {
        this.list = new ThreadSafeDynamicArrayList<>();
        list.add(1);
        list.add(2);
        list.add(3);
        list.add(4);
        list.add(5);
        list.add(6);
        list.add(7);
        list.add(8);
    }

    /**
     * Тест размер.
     */
    @Test
    public void whenGetSize() {
        assertThat(list.size(), is(8));
    }

    /**
     * Тест добавить в список.
     */
    @Test
    public void whenAddElements() {
        list.add(10);
        list.add(20);
        assertThat(list.size(), is(10));
        list.add(30);
        assertThat(list.size(), is(11));
    }

    /**
     * Тест проверяет конструктор.
     */
    @Test
    public void wenConstructorCapacity15() {
        ThreadSafeDynamicArrayList dynamicArrayList = new ThreadSafeDynamicArrayList(15);
        dynamicArrayList.add(1);
        assertThat(dynamicArrayList.size(), is(1));
        assertThat(dynamicArrayList.contains(1), is(true));
    }

    /**
     * Тест проверяет конструктор.
     */
    @Test
    public void wenConstructorCapacity0() {
        ThreadSafeDynamicArrayList dynamicArrayList = new ThreadSafeDynamicArrayList(0);
        assertThat(dynamicArrayList.size(), is(0));
    }

    /**
     * Тест проверяет конструктор.
     */
    @Test(expected = IllegalArgumentException.class)
    public void wenConstructorCapacityMinus1() {
        ThreadSafeDynamicArrayList dynamicArrayList = new ThreadSafeDynamicArrayList(-1);
        assertThat(dynamicArrayList.size(), is(0));
    }

    /**
     * Тест найти элемент в списке.
     */
    @Test
    public void whenValidIndexThenGetElement() {
        Integer result  = list.get(7);
        assertThat(result, is(8));
    }

    /**
     * Тест iterator().
     */
    @Test
    public void hasNextSequentialInvocation() {
        Iterator<Integer> it = list.iterator();
        assertThat(it.hasNext(), is(true));
        assertThat(it.next(), is(1));
        assertThat(it.hasNext(), is(true));
        assertThat(it.next(), is(2));
        assertThat(it.hasNext(), is(true));
        assertThat(it.next(), is(3));
        assertThat(it.hasNext(), is(true));
        assertThat(it.next(), is(4));
        assertThat(it.hasNext(), is(true));
        assertThat(it.next(), is(5));
        assertThat(it.hasNext(), is(true));
        assertThat(it.next(), is(6));
        assertThat(it.hasNext(), is(true));
        assertThat(it.next(), is(7));
        assertThat(it.hasNext(), is(true));
        assertThat(it.next(), is(8));
        assertThat(it.hasNext(), is(false));
    }
    /**
     * Тест найти элемент в списке.
     */
    @Test(expected = IndexOutOfBoundsException.class)
    public void whenInvalidIndexThenIndexOutOfBoundsException() {
        list.get(100);
    }

    /**
     * Тест iterator().
     */
    @Test(expected = NoSuchElementException.class)
    public void shouldThrowNoSuchElementException() {
        list = new ThreadSafeDynamicArrayList<>();
        Iterator<Integer> it = list.iterator();
        it.next();
    }

    @Test
    public void shouldThrowNoSuchElement() {
        list = new ThreadSafeDynamicArrayList<>();
        list.add(null);
        assertThat(list.indexOf(null), is(0));
        list.add(null);
        assertThat(list.indexOf(null), is(0));
    }
}