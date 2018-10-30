package ru.shifu.generic;

import org.junit.Before;
import org.junit.Test;
import ru.shifu.generic.exception.FullArrayException;
import ru.shifu.generic.exception.NoItemsException;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;
/**
 * RoleStoreTest.
 *
 * @author Pavel Abrikosov (abrikosovp@mail.ru).
 * @version 1.
 * @since 29.10.2018.
 **/
public class RoleStoreTest {

    RoleStore roleStore;
    Role role;

    /**
     * Действия которые выполняются по умолчанию
     * @throws FullArrayException
     */
    @Before
    public void setUP() throws FullArrayException {
        roleStore = new RoleStore(3);
        role = new Role("One");
        roleStore.add(role);
    }

    /**
     * Тест добавления элемента
     */
    @Test
    public void whenAddUserModel() {
        Role result = this.roleStore.findById("One");
        assertThat(this.role.equals(result), is(true));
    }

    /**
     * Тест заменить элемент.
     */
    @Test
    public void whenReplaceUserModelTrue() {
        Role roleB = new Role("Two");
        this.roleStore.replace("One", roleB);
        Role result = this.roleStore.findById("Two");
        assertThat(roleB.equals(result), is(true));
    }
    /**
     * Тест заменить элемент.
     */
    @Test
    public void whenReplaceByNotCorrectIdUserModelFalse() {
        Role roleB = new Role("Two");
        assertThat(this.roleStore.replace("Reba", roleB), is(false));
    }

    /**
     * Тест удалить элемент.
     * @throws NoItemsException
     * @throws FullArrayException
     */
    @Test
    public void whenDeleteUserModel() throws NoItemsException, FullArrayException {
        this.roleStore.add(new Role("Two"));
        this.roleStore.add(new Role("Three"));
        this.roleStore.delete("One");
        Role result = this.roleStore.findById("One");
        assertThat(null, is(result));
    }
    /**
     * Тест удалить элемент.
     * @throws NoItemsException
     * @throws FullArrayException
     */
    @Test
    public void whenDeleteByNotCorrectIdUserModelFalse() throws NoItemsException, FullArrayException {
        this.roleStore.add(new Role("Two"));
        this.roleStore.add(new Role("Three"));

        assertThat(this.roleStore.delete("Refer"), is(false));
    }

    /**
     * Тест найти элемент по ID
     */
    @Test
    public void findByIdUserModel() {
        Role result = roleStore.findById("One");
        assertThat(role, is(result));
    }
}
