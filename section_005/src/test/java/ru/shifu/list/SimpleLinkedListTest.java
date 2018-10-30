package ru.shifu.list;

import org.junit.Before;
import org.junit.Test;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;
/**
 * SimpleLinkedListTest.
 *
 * @author Pavel Abrikosov (abrikosovp@mail.ru).
 * @version 1.
 * @since 30.10.2018.
 **/
public class SimpleLinkedListTest {

    SimpleLinkedList<Integer> list;
    Iterator<Integer> iterator;

    @Before
    public void beforeTest() {
        list = new SimpleLinkedList<>();
        list.add(1000);
        list.add(200);
        list.add(30);
    }

    /**
     * Тест размер списка.
     */
    @Test
    public void size() {
        assertThat(this.list.size(), is(3));
    }

    /**
     * Тест add, get элемент.
     */
    @Test
    public void add() {
        assertThat(this.list.get(2), is(30));
    }

    /**
     * Тест удалить
     */
    @Test
    public void delete() {
        this.list.delete();
        assertThat(this.list.get(1), is(30));
    }

    /**
     * Тест удалить
     */
    @Test
    public void wenAddIdNullByDelete() {
        SimpleLinkedList<Integer> list = new SimpleLinkedList<>();
        Integer result = list.delete();

        assertThat(null, is(result));
    }

    /**
     * Тест достать элемент
     */
    @Test(expected = NullPointerException.class)
    public  void gatException() {
        assertThat(this.list.get(100), is(false));
    }

    /**
     * Тест итератор
     */
    @Test
    public void thenIterator() {
        this.iterator = this.list.iterator();
        assertThat(this.iterator.hasNext(), is(true));
        assertThat(this.iterator.next(), is(1000));
        assertThat(this.iterator.hasNext(), is(true));
        assertThat(this.iterator.next(), is(200));
        assertThat(this.iterator.hasNext(), is(true));
        assertThat(this.iterator.next(), is(30));
        assertThat(this.iterator.hasNext(), is(false));
    }

    /**
     * Тест итератор
     */
    @Test(expected = NoSuchElementException.class)
    public void iteratorNoSuchException() {
        this.iterator = this.list.iterator();
        assertThat(this.iterator.next(), is(1000));
        assertThat(this.iterator.next(), is(200));
        assertThat(this.iterator.next(), is(30));
        assertThat(this.iterator.next(), is(10000));
    }

    /**
     * Тест итератор
     */
    @Test(expected = ConcurrentModificationException.class)
    public void iteratorModificationException() {
        this.iterator = this.list.iterator();
        this.iterator.next();
        this.list.add(4);
        this.iterator.next();
    }
}