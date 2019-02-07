package ru.shifu.userstorage.logic;


import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import ru.shifu.userstorage.models.Role;
import ru.shifu.userstorage.models.User;


import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;
/**
 * ValidateServiceTest.
 *
 * @author Pavel Abrikosov (abrikosovp@mail.ru)
 * @version 0.5$
 * @since 0.1
 * 02.02.2019
 */

public class ValidateServiceTest {
    private Validate validate = ValidateService.getInstance();

    @Before
    public void beforeTest() {
        this.validate.fullDelete();
        Action.Type add = Action.Type.valueOf("ADD");
        this.validate.doAction(add, new User("123", "Katy", "ewLogin", Role.ADMIN, "NewEmail"));
    }

    @After
    public void afterTest() {
        this.validate.fullDelete();
    }

    @Test
    public void whenAddUsersThenReturnResult() {
        assertThat(this.validate.findAll().size(), is(1));
    }

    @Test
    public void whenUpdateUsersThenReturnResult() {
        Action.Type update = Action.Type.valueOf("UPDATE");
        this.validate.doAction(update, new User(this.getId(), "name", "Fre", "Login", Role.USER, "Email"));
        assertThat(this.validate.findAll().get(0).getName(), is("name"));
    }

    @Test
    public void whenDeleteUsersThenReturnResult() {
        Action.Type update = Action.Type.valueOf("DELETE");
        this.validate.doAction(update, new User(this.getId(), "name", "Fre", "Login", Role.USER, "Email"));
        assertThat(this.size(), is(0));
    }

    @Test
    public void whenIsRegisteredUsersThenReturnResult() {
        long result = this.validate.isRegistered("Katy", "ewLogin");
        assertThat(this.getId(), is(String.valueOf(result)));
        long res = this.validate.isRegistered("Kat", "Login");
        assertThat(-1L, is(res));
    }

    @Test
    public void whenAddIDGenerateUsersThenReturnResult() {
        Action.Type add = Action.Type.valueOf("ADD");
        this.validate.doAction(add, new User("1", "Kat", "Login", Role.USER, "Email"));
        assertThat(this.size(), is(2));
    }

    private String getId() {
        return this.validate.findAll().get(0).getId();
    }

    private int size() {
        return this.validate.findAll().size();
    }
}