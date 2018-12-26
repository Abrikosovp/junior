package ru.shifu.tracker;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Before;
import org.junit.Test;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;
import java.util.Properties;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;

public class TrackerSQLTest {

    private Item item;

    private static final Logger LOG = LogManager.getLogger(TrackerSQLTest.class);
    private static final String CONFIG = "tracker.properties";

    @Before
    public void testBefore() {
        item = new Item("TestName", "TestDescription");
    }

    public Connection init() {
        try (InputStream in = TrackerSQL.class.getClassLoader().getResourceAsStream(CONFIG)) {
            Properties config = new Properties();
            config.load(in);
            Class.forName(config.getProperty("driver"));
            return DriverManager.getConnection(
                    config.getProperty("url"),
                    config.getProperty("user"),
                    config.getProperty("password")
            );
        } catch (Exception e) {
            throw new IllegalStateException(e);
        }
    }
    /**
     * Тест add.
     */
    @Test
    public void whenAddNewItemTrackerHasSameItem() {
        try (TrackerSQL tracker = new TrackerSQL(ConnectionRollback.create(this.init()))) {
            Item result = tracker.add(this.item);
            assertThat(result, is(this.item));
        } catch (SQLException e) {
            LOG.error(e.getMessage(), e);
        } catch (Exception e) {
            LOG.error(e.getMessage(), e);
        }
    }
    /**
     * Тест replace.
     */
    @Test
    public void whenAddFirstItemAndReplaceItSecondItemThenTrackerHasSecondItem() {
        try (TrackerSQL tracker = new TrackerSQL(ConnectionRollback.create(this.init()))) {
            tracker.add(this.item);
            Item second = new Item("test2 replace", "Test2Description replace");
            tracker.replace(this.item.getId(), second);
            assertThat(tracker.findById(this.item.getId()).getName(), is("test2 replace"));
        } catch (SQLException e) {
            LOG.error(e.getMessage(), e);
        } catch (Exception e) {
            LOG.error(e.getMessage(), e);
        }
    }
    /**
     * Тест delete.
     */
    @Test
    public void whenAddItemAndDeleteItemThenNull() {
        try (TrackerSQL tracker = new TrackerSQL(ConnectionRollback.create(this.init()))) {
            tracker.add(this.item);
            boolean result = tracker.delete(this.item.getId());
            assertThat(result, is(true));
        } catch (SQLException e) {
            LOG.error(e.getMessage(), e);
        } catch (Exception e) {
            LOG.error(e.getMessage(), e);
        }
    }
        /**
     * Тест findByName.
     */
    @Test
    public void whenAddItemAndFindNameThenItem() {
            try (TrackerSQL tracker = new TrackerSQL(ConnectionRollback.create(this.init()))) {
                tracker.add(this.item);
                Item result = tracker.findByName("TestName").get(0);
                assertThat(result.getName(), is("TestName"));
            } catch (SQLException e) {
                LOG.error(e.getMessage(), e);
            } catch (Exception e) {
                LOG.error(e.getMessage(), e);
            }
        }
    /**
     * Тест findById.
     */
    @Test
    public void whenAddItemAndFindIdThenItem() {
        try (TrackerSQL tracker = new TrackerSQL(ConnectionRollback.create(this.init()))) {
            tracker.add(this.item);
            assertThat(tracker.findById(this.item.getId()), is(this.item));
        } catch (SQLException e) {
            LOG.error(e.getMessage(), e);
        } catch (Exception e) {
            LOG.error(e.getMessage(), e);
        }
    }
    /**
     * Тест getAll.
     */
    @Test
    public void whenGetAllItemThenListContainsItems() {
        try (TrackerSQL tracker = new TrackerSQL(ConnectionRollback.create(this.init()))) {
            tracker.add(this.item);
            List<Item> expect = tracker.getAll();
            assertThat(expect.isEmpty(), is(false));
        } catch (SQLException e) {
            LOG.error(e.getMessage(), e);
        } catch (Exception e) {
            LOG.error(e.getMessage(), e);
        }
    }
}