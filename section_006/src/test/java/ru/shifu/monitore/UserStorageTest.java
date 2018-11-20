package ru.shifu.monitore;
import org.junit.Before;
import org.junit.Test;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * UserStorageTest.
 *
 * @author Pavel Abrikosov (abrikosovp@mail.ru).
 * @version 1.
 * @since 20.11.2018.
 **/
public class UserStorageTest {
    UserStorage userStorage;
    User user;
    boolean result;

    @Before
    public void beforeTest() {
        this.userStorage = new UserStorage();
        this.user = new User(1, 200);
        this.result = this.userStorage.add(this.user);
    }

    /**
     * Тест add
     */
    @Test
    public void wenAddNewUsers() {
        assertThat(this.result, is(true));
        this.result = this.userStorage.add(new User(1, 5000));
        assertThat(this.result, is(false));
    }

    /**
     * Тест update
     */
    @Test
    public void whenUpdateUserReturnTrueOrFalse() {
       assertThat(this.userStorage.update(new User(1, 300)), is(true));
       assertThat(this.userStorage.update(new User(2, 300)), is(false));
    }

    /**
     * Тест Delete
     */
    @Test
    public void whenDeleteUserReturnTrueOrFalse() {
        assertThat(this.userStorage.delete(this.user), is(true));
        assertThat(this.userStorage.delete(new User(2, 300)), is(false));
    }

    /**
     * Тест transfer
     */
    @Test
    public void whenTransferFromUser1ToUser2() {
        User from = new User(2, 3000);
        this.userStorage.add(from);
        assertThat(this.userStorage.transfer(2, 1, 2000), is(true));
        assertThat(this.userStorage.transfer(1, 2, 20000), is(false));
        assertThat(this.userStorage.transfer(4, 2, 20000), is(false));
        assertThat(this.userStorage.transfer(5, 2, 20000), is(false));
        assertThat(from.transfer(null, 400), is(false));
        assertThat(from.transfer(user, 10000), is(false));
    }
}