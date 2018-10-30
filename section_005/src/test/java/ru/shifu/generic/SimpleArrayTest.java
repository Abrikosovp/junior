package ru.shifu.generic;

import org.junit.Before;
import org.junit.Test;
import ru.shifu.generic.exception.FullArrayException;
import ru.shifu.generic.exception.NoItemsException;

import java.util.Iterator;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
/**
 * SimpleArrayTest.
 *
 * @author Pavel Abrikosov (abrikosovp@mail.ru).
 * @version 1.
 * @since 27.10.2018.
 **/

public class SimpleArrayTest {
    private final SimpleArray<Integer> array = new SimpleArray<>(5);

    @Before
    public void setOp() throws FullArrayException {
        this.array.add(1);
        this.array.add(2);
        this.array.add(3);
    }

    /**
     * Тест - добавить элемент.
     * @throws FullArrayException
     */
    @Test
    public void whenAdd() throws FullArrayException {
        this.array.add(4);
        this.array.add(5);
        assertThat(this.array.get(3), is(4));
        assertThat(this.array.get(4), is(5));
    }

    /**
     * Тест показывает что массив статический , и он переполнен.
     * @throws FullArrayException
     */
    @Test(expected = FullArrayException.class)
    public void whenAddException() throws FullArrayException {
        this.array.add(4);
        this.array.add(5);
        this.array.add(6);
    }

    /**
     * Тест устанавливает элемент(перезаписывает)
     */
    @Test
    public void wenSet() {
        this.array.set(2, 100);
        this.array.set(1, 600);
        assertThat(this.array.get(2), is(100));
        assertThat(this.array.get(1), is(600));
    }

    /**
     * Тест удаления элемента
     * @throws FullArrayException
     */
    @Test
    public void wenDelete() throws FullArrayException, NoItemsException {
        this.array.add(23);
        this.array.add(23);
        this.array.delete(1);
        assertThat(this.array.get(1), is(3));
    }
    /**
     * Тест удаления элемента
     * @throws FullArrayException
     */
    @Test(expected = NoItemsException.class)
    public void whenDeleteNotEqualToZero() throws NoItemsException {
        SimpleArray<Integer> simpleArray = new SimpleArray<>(0);
        Integer expect = null;
        simpleArray.delete(0);
        assertThat(simpleArray, is(expect));
    }

    /**
     * Тест получть элемент.
     */
    @Test
    public void get() {
        assertThat(array.get(2), is(3));
    }

    /**
     * Тест получть элемент.
     */
    @Test(expected = IndexOutOfBoundsException.class)
    public void wenGetInvalidIndex() {
        this.array.get(15);
    }

    @Test
    public void wenIteratorHashNextByNext() throws FullArrayException {
        SimpleArray<Integer> array = new SimpleArray<>(5);
        array.add(1);
        array.add(2);
        array.add(3);
        Iterator<Integer> iterator = array.iterator();
        assertThat(iterator.hasNext(), is(true));
        assertThat(iterator.hasNext(), is(true));
        assertThat(iterator.next(), is(1));
        assertThat(iterator.next(), is(2));
    }

    @Test
    public void wenIteratorHashNextByNextFalse() throws FullArrayException {
        SimpleArray<Integer> array = new SimpleArray<>(3);
        array.add(1);
        array.add(2);
        array.add(3);
        Iterator<Integer> iterator = array.iterator();
        assertThat(iterator.hasNext(), is(true));
        assertThat(iterator.next(), is(1));
        assertThat(iterator.hasNext(), is(true));
        assertThat(iterator.next(), is(2));
        assertThat(iterator.hasNext(), is(true));
        assertThat(iterator.next(), is(3));
        assertThat(iterator.hasNext(), is(false));
    }
}
