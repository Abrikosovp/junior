package ru.shifu.generic;

import org.junit.Before;
import org.junit.Test;
import ru.shifu.generic.exception.FullArrayException;
import ru.shifu.generic.exception.NoItemsException;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;
/**
 * UserStoreTest.
 *
 * @author Pavel Abrikosov (abrikosovp@mail.ru).
 * @version 1.
 * @since 29.10.2018.
 **/
public class UserStoreTest {

    UserStore userStore;
    User user;

    /**
     * Действия которые выполняются по умолчанию
     * @throws FullArrayException
     */
    @Before
    public void setUP() throws FullArrayException {
        userStore = new UserStore(3);
        user = new User("One");
        userStore.add(user);
    }

    /**
     * Тест добавления элемента
     */
    @Test
    public void whenAddUserModel() {
        User result = this.userStore.findById("One");
        assertThat(user.equals(result), is(true));
    }

    /**
     * Тест заменить элемент.
     */
    @Test
    public void whenReplaceUserModel() {
        User userB = new User("Two");
        this.userStore.replace("One", userB);
        User result = this.userStore.findById("Two");
        assertThat(userB.equals(result), is(true));
    }

    /**
     * Тест удалить элемент.
     * @throws NoItemsException
     * @throws FullArrayException
     */
    @Test
    public void whenDeleteUserModel() throws NoItemsException, FullArrayException {
        this.userStore.add(new User("Two"));
        this.userStore.add(new User("Three"));
        this.userStore.delete("One");
        User result = this.userStore.findById("One");
        assertThat(null, is(result));
    }

    /**
     * Тест найти элемент по ID
     */
    @Test
    public void findByIdUserModel() {
        User result = userStore.findById("One");
        assertThat(user, is(result));
    }
}
