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
    private ValidateService validate = ValidateService.getInstance().init();

    @Before
    public void beforeTest() {
        this.validate.fullDelete();
        Action.Type add = Action.Type.valueOf("ADD");
        this.validate.doAction(add, new User("1","123", "Katy", "ewLogin", Role.ADMIN, "NewEmail"));
    }

    @After
    public void afterTest() {
        this.validate.fullDelete();
    }

    @Test
    public void whenAddUsersThenReturnResult() {
        this.validate.findById("1");
        assertThat(this.validate.findById("1").getName(), is("123"));

    }

    @Test
    public void whenUpdateUsersThenReturnResult() {
        Action.Type update = Action.Type.valueOf("UPDATE");
        this.validate.doAction(update, new User("1","1", "Fre", "Login", Role.USER, "Email"));
        this.validate.findById("1");
        assertThat(this.validate.findById("1").getName(), is("1"));
    }

    @Test
    public void whenDeleteUsersThenReturnResult() {
        Action.Type update = Action.Type.valueOf("DELETE");
        this.validate.doAction(update, new User("1","1", "Fre", "Login", Role.USER, "Email"));
        assertThat(this.validate.findAll().size(), is(0));
    }

    @Test
    public void whenIsRegisteredUsersThenReturnResult() {
        long result = this.validate.isRegistered("Katy", "ewLogin");
        assertThat(1L, is(result));
        long res = this.validate.isRegistered("Kat", "Login");
        assertThat(-1L, is(res));
    }

    @Test
    public void whenAddIDGenerateUsersThenReturnResult() {
        Action.Type add = Action.Type.valueOf("ADD");
        this.validate.doAction(add, new User("1", "Kat", "Login", Role.USER, "Email"));
        assertThat(this.validate.findAll().size(), is(2));
    }
}