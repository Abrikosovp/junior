package ru.shifu.tracker;

import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;

public class TrackerSQLTest {

    Item item;
    @Before
    public void testBefore() {
        item = new Item("TestName", "TestDescription");
    }
    /**
     * Тест add.
     */
    @Test
    public void whenAddNewItemTrackerHasSameItem() {
        try (TrackerSQL tracker = new TrackerSQL()) {
            Item result = tracker.add(this.item);
            assertThat(result, is(this.item));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    /**
     * Тест replace.
     */
    @Test
    public void whenAddFirstItemAndReplaceItSecondItemThenTrackerHasSecondItem() {
        try (TrackerSQL tracker = new TrackerSQL()) {
            tracker.add(this.item);
            Item second = new Item("test2 replace", "Test2Description replace");
            tracker.replace(this.item.getId(), second);
            assertThat(tracker.findById(this.item.getId()).getName(), is("test2 replace"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    /**
     * Тест delete.
     */
    @Test
    public void whenAddItemAndDeleteItemThenNull() {
        try (TrackerSQL tracker = new TrackerSQL()) {
            tracker.add(this.item);
            boolean result = tracker.delete(this.item.getId());
            assertThat(result, is(true));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    /**
     * Тест findByName.
     */
    @Test
    public void whenAddItemAndFindNameThenItem() {
        try (TrackerSQL tracker = new TrackerSQL()) {
            tracker.add(this.item);
            Item result = tracker.findByName("TestName").get(0);
            assertThat(result.getName(), is("TestName"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    /**
     * Тест findById.
     */
    @Test
    public void whenAddItemAndFindIdThenItem() {
        try (TrackerSQL tracker = new TrackerSQL()) {
            tracker.add(this.item);
            assertThat(tracker.findById(this.item.getId()), is(this.item));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    /**
     * Тест getAll.
     */
    @Test
    public void whenGetAllItemThenListContainsItems() {
        try (TrackerSQL tracker = new TrackerSQL()) {
            tracker.add(this.item);
            List<Item> expect = tracker.getAll();
            assertThat(expect.isEmpty(), is(false));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}