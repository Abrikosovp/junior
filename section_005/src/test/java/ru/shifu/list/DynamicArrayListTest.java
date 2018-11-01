package ru.shifu.list;

import org.junit.Before;
import org.junit.Test;
import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;
/**
 * DynamicArrayListTest.
 *
 * @author Pavel Abrikosov (abrikosovp@mail.ru).
 * @version 1.
 * @since 30.10.2018.
 **/
public class DynamicArrayListTest {
    private DynamicArrayList<Integer> list;

    @Before
    public void beforeTest() {
        this.list = new DynamicArrayList<>();
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
        DynamicArrayList dynamicArrayList = new DynamicArrayList(15);
        dynamicArrayList.add(1);
        assertThat(dynamicArrayList.size(), is(1));
    }

    /**
     * Тест проверяет конструктор.
     */
    @Test
    public void wenConstructorCapacity0() {
        DynamicArrayList dynamicArrayList = new DynamicArrayList(0);
        assertThat(dynamicArrayList.size(), is(0));
    }

    /**
     * Тест проверяет конструктор.
     */
    @Test(expected = IllegalArgumentException.class)
    public void wenConstructorCapacityMinus1() {
        DynamicArrayList dynamicArrayList = new DynamicArrayList(-1);
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
    @Test(expected = ConcurrentModificationException.class)
    public void shouldConcurrentModificationException() {
        Iterator<Integer> it = list.iterator();
        it.next();
        list.add(66);
        it.next();
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
        list = new DynamicArrayList<>();
        Iterator<Integer> it = list.iterator();
        it.next();
    }

    @Test
    public void shouldThrowNoSuchElement() {
        list = new DynamicArrayList<>();
        list.add(null);
        assertThat(list.indexOf(null), is(0));
        list.add(null);
        assertThat(list.indexOf(null), is(0));
    }
}