package ru.shifu.map;

import org.junit.Before;
import org.junit.Test;
import java.util.ConcurrentModificationException;
import java.util.GregorianCalendar;
import java.util.Iterator;
import java.util.NoSuchElementException;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;
/**
 * SimpleHashMapTest.
 *
 * @author Pavel Abrikosov (abrikosovp@mail.ru).
 * @version 1.
 * @since 3.11.2018.
 **/
public class SimpleHashMapTest {

    private SimpleHashMap<User, String> map;

    private User first = new User(
            "Павел",
            2,
            new GregorianCalendar(1987, 9, 15)
    );
    private User second = new User(
            "Леонид",
            9,
            new GregorianCalendar(2000, 3, 1)
    );
    @Before
    public void setUP() {
        this.map = new SimpleHashMap<>();
    }
    /**
     * Тест insert.
     */
    @Test
    public void whenInsertElement() {
        this.map.insert(this.first, "first");
        this.map.insert(this.second, "second");
        assertThat(this.map.size(), is(2));
    }
    /**
     * Тест insert.
     */
    @Test
    public void whenChangeTheValueOfTheElement() {
        this.map.insert(this.first, "first");
        assertThat(this.map.size(), is(1));
        assertThat(this.map.get(this.first), is("first"));
        this.map.insert(this.first, "second");
        assertThat(this.map.size(), is(1));
        assertThat(this.map.get(this.first), is("second"));
    }
    /**
     * Тест get.
     */
    @Test
    public void whenGetElementValueByKey() {
        this.map.insert(this.first, "first");

        String result = this.map.get(this.first);
        assertThat(result, is("first"));

        String expect = this.map.get(this.second);
        assertThat(null, is(this.map.get(this.second)));
    }
    /**
     * Тест delete.
     */
    @Test
    public void whenDeleteElementByKey() {
        this.map.insert(this.first, "first");
        boolean result = this.map.delete(this.first);
        assertThat(result, is(true));
        result = this.map.delete(this.second);
        assertThat(result, is(false));
    }
    /**
     * Тест iterator.
     */
    @Test
    public void hasNextSequentialInvocation() {
        this.map.insert(this.first, "first");
        Iterator<SimpleHashMap.Node<User, String>> it = this.map.iterator();
        assertThat(it.hasNext(), is(true));
        String result = it.next().getValue();
        assertThat(result, is("first"));
        assertThat(it.hasNext(), is(false));
    }
    /**
     * Тест iterator.
     */
    @Test(expected = NoSuchElementException.class)
    public void shouldThrowNoSuchElementException() {
        Iterator<SimpleHashMap.Node<User, String>> it = this.map.iterator();
        it.next();
    }
    /**
     * Тест iterator().
     */
    @Test(expected = ConcurrentModificationException.class)
    public void shouldConcurrentModificationException() {
        Iterator<SimpleHashMap.Node<User, String>> it = this.map.iterator();
        assertThat(it.hasNext(), is(false));
        this.map.insert(this.first, "first");
        it.next();
    }

    private User three = new User(
            "Ангелина",
            1,
            new GregorianCalendar(1990, 1, 1)
    );
    private User four = new User(
            "Марина",
            2,
            new GregorianCalendar(1991, 2, 2)
    );
    private User five = new User(
            "Вечислав",
            3,
            new GregorianCalendar(2015, 3, 3)
    );
    private User six = new User(
            "Леонид",
            4,
            new GregorianCalendar(1999, 4, 4)
    );
    private User seven = new User(
            "Иосив",
            5,
            new GregorianCalendar(1998, 5, 5)
    );
    private User eight = new User(
            "Федор",
            6,
            new GregorianCalendar(2001, 6, 6)
    );
    private User nine = new User(
            "Екатерина",
            7,
            new GregorianCalendar(2010, 7, 7)
    );
    private User ten = new User(
            "Евгений",
            8,
            new GregorianCalendar(2009, 8, 8)
    );
    private User eleven = new User(
            "Залина",
            9,
            new GregorianCalendar(2008, 9, 9)
    );
    private User twelve = new User(
            "Аня",
            10,
            new GregorianCalendar(2000, 10, 10)
    );
    private User thirteen = new User(
            "Марта",
            11,
            new GregorianCalendar(2008, 11, 13)
    );
    private User cheat = new User(
            "Константин",
            12,
            new GregorianCalendar(1997, 12, 14)
    );

    /**
     * Тест - resize.
     */
    @Test
    public void resizeTest() {
        this.map.insert(this.first, "first");
        this.map.insert(this.second, "second");
        this.map.insert(this.three, "three");
        this.map.insert(this.four, "four");
        this.map.insert(this.five, "five");
        this.map.insert(this.six, "six");
        this.map.insert(this.seven, "seven");
        this.map.insert(this.eight, "eight");
        this.map.insert(this.nine, "nine");
        this.map.insert(this.ten, "ten");
        this.map.insert(this.eleven, "eleven");
        this.map.insert(this.twelve, "twelve");
        this.map.insert(this.thirteen, "thirteen");
        this.map.insert(this.cheat, "cheat");
        assertThat(this.map.size(), is(12));
    }
}
