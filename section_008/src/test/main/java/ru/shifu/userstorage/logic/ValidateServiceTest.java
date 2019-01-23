package ru.shifu.userstorage.logic;

import org.junit.Test;
import ru.shifu.userstorage.persistent.User;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;
/**
 * ValidateServiceTest.
 *
 * @author Pavel Abrikosov (abrikosovp@mail.ru)
 * @version 0.1$
 * @since 0.1
 * 18.01.2019
 */
public class ValidateServiceTest {

    private final ValidateService validate = ValidateService.getInstance().init();

    @Test
    public void addAndUpdateAndDelete() {

        String actionAdd = "add";
        Action.Type actAdd = Action.Type.valueOf(actionAdd.toUpperCase());
        this.validate.doAction(actAdd, new User("1", "first", "first login", "first@mail.ru"));
        assertThat(this.validate.findAll().size(), is(1));

        assertThat(this.validate.findById("1").getId(), is("1"));

        String action = "update";
        Action.Type act = Action.Type.valueOf(action.toUpperCase());
        this.validate.doAction(act, new User("1", "second", "first login", "first@mail.ru"));
        assertThat(this.validate.findAll().get(0).getName(), is("second"));

        String actionDell = "delete";
        Action.Type actDell = Action.Type.valueOf(actionDell.toUpperCase());
        this.validate.doAction(actDell, new User("1", "second", "first login", "first@mail.ru"));
        assertThat(this.validate.findAll().size(), is(0));
    }
}