package ru.shifu.tracker;
import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;
/**
 * TrackerTest .
 *
 * @author Pavel Abrikosov (abrikosovp@mail.ru).
 * @version 1.
 * @since 09.10.2018.
 **/
public class TrackerTest {
    /**
     * Тест - метод реализаущий добавление заявки в хранилище
     */
    @Test
    public void whenAddNewItemThenTrackerHasSameItem() {
        Tracker tracker = new Tracker();
        Item one = tracker.add(new Item("test", "testDesc", 123L));
        assertThat(tracker.findAll()[0], is(one));
    }
    /**
     *  Тест - редактирование заявок.
     */
    @Test
    public void whenReplaceNameThenReturnNewName() {
        Tracker tracker = new Tracker();
        Item one = tracker.add(new Item("test", "testDesc", 123L));
        Item two = new Item("test2", "testDesc", 123L);
        two.setId(one.getId());
        tracker.replace(one.getId(), two);
        assertThat(tracker.findById(one.getId()).getName(), is("test2"));
    }
    /**
     * Тест - удаление заявок.
     */
    @Test
    public void whenDeleteItemThenReturnItemMinusOne() {
        Tracker tracker = new Tracker();
        Item one = tracker.add(new Item("test", "testDesc", 123L));
        Item two = tracker.add(new Item("test2", "testDesc", 123L));
        tracker.delete(one.getId());
        Item[] value = new Item[] {two, null};
        assertThat(tracker.findAll(), is(value));
    }
    /**
     * Тест - метод получение списка всех заявок.
     */
    @Test
    public void whenFindAllItemThenReturnCopyArray() {
        Tracker tracker = new Tracker();
        Item one = tracker.add(new Item("test", "testDesc", 123L));
        Item two = tracker.add(new Item("test2", "testDesc", 123L));
        Item[] value = new Item[] {one, two};
        assertThat(tracker.findAll(), is(value));
    }
    /**
     * Тест - метод получение списка по имени.
     */
    @Test
    public void whenFindByNameItemThenReturnCopyArray() {
        Tracker tracker = new Tracker();
        Item one = tracker.add(new Item("test", "testDesc", 123L));
        Item[] value = new Item[] {one};
        assertThat(tracker.findByName("test"), is(value));
    }
    /**
     * Тест - метод получение заявки по id.
     */
    @Test
    public void whenFindByIdThenTrackerFidsItem() {
        Tracker tracker = new Tracker();
        Item one = tracker.add(new Item("test", "testDesc", 123L));
        assertThat(tracker.findById(one.getId()), is(one));
    }
}
