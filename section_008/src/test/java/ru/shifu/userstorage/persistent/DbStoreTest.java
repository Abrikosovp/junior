package ru.shifu.userstorage.persistent;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import ru.shifu.userstorage.models.Role;
import ru.shifu.userstorage.models.User;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;
/**
 * DbStoreTest.
 *
 * @author Pavel Abrikosov (abrikosovp@mail.ru)
 * @version 0.5$
 * @since 0.1
 * 02.02.2019
 */
public class DbStoreTest {

    private User two;
    private DbStore db = DbStore.getInstance();

    @Before
    public void beforeTest() {
        this.db.fullDelete();
        this.db.add(new User("123", "Ken", "ewLogin", Role.ADMIN, "NewEmail"));
        this.two = new User("1", "123", "Katy", "ewLogin", Role.ADMIN, "NewEmail");
    }

    @After
    public void afterTest() {
        assertTrue(this.db.fullDelete());
    }

    @Test
    public void whenUpdateUsersThenReturnResult() {
        assertTrue(db.update(two));
    }

    @Test
    public void whenFindAllUsersThenReturnResult() {
        assertThat(db.findAll().size(), is(1));
    }

    @Test
    public void whenFindIdUsersThenReturnResult() {
        assertThat(db.findById(db.findAll().get(0).getId()).getName(), is("123"));
    }

    @Test
    public void whenValidateUsersThenReturnResult() {
        assertThat(db.validate(two), is(false));
    }

    @Test
    public void whenDeleteUsersThenReturnResult() {
        assertTrue(db.delete(two));
    }
}