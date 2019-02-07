package ru.shifu.userstorage.persistent;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import ru.shifu.userstorage.models.Role;
import ru.shifu.userstorage.models.User;


import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;
/**
 * MemoryStoreTest.
 *
 * @author Pavel Abrikosov (abrikosovp@mail.ru)
 * @version 0.5$
 * @since 0.1
 * 02.02.2019
 */
public class MemoryStoreTest {

    private final MemoryStore store = MemoryStore.getInstance();

    private User user1;
    private User user2;
    private User user3;
    private User findBy;

    @Before
    public void before() {
        this.findBy = new User("1", "updated first", "updated first login", "pass", Role.ADMIN, "ufirst@mail.ru");
        this.user1 = new User("1", "first", "first login", "pass", Role.ADMIN, "first@mail.ru");
        this.user2 = new User("2", "second", "second login", "pass", Role.ADMIN, "second@mail.ru");
        this.user3 = new User("2", "second", "second login", "pass", Role.ADMIN, "second@mail.ru");
        this.store.add(this.user1);
        this.store.add(this.user2);
        this.store.add(this.user3);
    }

    @After
    public void afterTest() {
        store.fullDelete();
    }

    @Test
    public void whenAddTwoDifferentUsersThanSizeIsTwo() {
        assertThat(this.store.findAll().size(), is(2));
    }

    @Test
    public void whenUpdateUserThanUpdated() {
        this.store.update(this.findBy);
        assertThat(this.store.findById(this.findBy.getId()).getName(), is("updated first"));

        assertThat(
                this.store.update(
                        new User("33", "updated first", "updated first login", "pass", Role.ADMIN, "ufirst@mail.ru")),
                is(false));
    }

    @Test
    public void whenDeleteUserThanDeleted() {
        assertTrue(this.store.delete(this.user1));
        assertNull(this.store.findById(this.user1.getId()));
        assertThat(this.store.delete(new User("22", "updated first", "updated first login", "pass", Role.ADMIN, "ufirst@mail.ru")), is(false));
        User user = new User("33", "updated first", "updated first login", "pass", Role.ADMIN, "ufirst@mail.ru");
        User user2 = new User("33", "updated first", "updated first login", "pass", Role.ADMIN, "ufirst@mail.ru");
        assertThat(user2.toString(), is(user.toString()));
    }

    @Test
    public void whenDeleteUserThanDeletedTest2() {
        MemoryStore def = MemoryStore.getInstance();
        def.add(new User("33", "updated first", "updated first login", "pass", Role.ADMIN, "ufirst@mail.ru"));
        User user = new User("33", "s", "l", "pass", Role.ADMIN, "e");
        Boolean result = def.delete(user);
        assertTrue(result);
    }
}