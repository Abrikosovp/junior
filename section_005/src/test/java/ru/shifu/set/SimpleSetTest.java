package ru.shifu.set;
import org.junit.Before;
import org.junit.Test;

import java.util.Iterator;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;
/**
 * SimpleSetTest.
 *
 * @author Pavel Abrikosov (abrikosovp@mail.ru).
 * @version 1.
 * @since 1.11.2018.
 **/
public class SimpleSetTest {
    SimpleSet set;

    @Before
    public void testBefore() {
        set = new SimpleSet();
        set.add(1);
        set.add(2);
        set.add(3);
        set.add(4);
        set.add(4);
    }

    @Test
    public void whenAddDuplicateElementThenNotAddElement() {
        assertThat(set.size(), is(4));
        set.add(1);
        assertThat(set.size(), is(4));
    }

    @Test
    public void hasNextSequentialInvocation() {
        set.add(2);
        set.add(3);
        Iterator<Integer> it = set.iterator();
        assertThat(it.next(), is(1));
        assertThat(it.next(), is(2));
        assertThat(it.next(), is(3));
        assertThat(it.next(), is(4));
        assertThat(it.hasNext(), is(false));
    }

    @Test
    public void whenAdd5ElementReturnSize4() {
        assertThat(set.size(), is(4));
    }

    @Test
    public void whenAdd0ElementReturnSize0() {
        SimpleSet simpleSet = new SimpleSet();
        assertThat(simpleSet.size(), is(0));
    }
}
