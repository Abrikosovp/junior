package ru.shifu.userstorage.logic;

import org.junit.Test;
import ru.shifu.userstorage.persistent.User;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;
/**
 * ValidateServiceTest.
 *
 * @author Pavel Abrikosov (abrikosovp@mail.ru)
 * @version 0.2$
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

    @Test
    public void addAndUpdateAndDelete2() {

        String actionAdd = "add";
        Action.Type actAdd = Action.Type.valueOf(actionAdd.toUpperCase());
        this.validate.doAction(actAdd, new User("first", "first login", "first@mail.ru"));
        assertThat(this.validate.findAll().size(), is(1));

        String id = this.validate.findAll().get(0).getId();

        String actionId = "add";
        Action.Type actId = Action.Type.valueOf(actionId.toUpperCase());
        this.validate.doAction(actId, new User(id,"mem", "pem", "@"));
        assertThat(this.validate.findAll().size(), is(1));

        String actionNull = "add";
        Action.Type actNull = Action.Type.valueOf(actionNull.toUpperCase());
        this.validate.doAction(actNull, null);
        assertThat(this.validate.findAll().size(), is(1));

        String actionLogin = "add";
        Action.Type actLogin = Action.Type.valueOf(actionLogin.toUpperCase());
        this.validate.doAction(actLogin, new User("rep", "first login", "first@"));
        assertThat(this.validate.findAll().size(), is(1));

        String actionEmail = "add";
        Action.Type actEmail = Action.Type.valueOf(actionEmail.toUpperCase());
        this.validate.doAction(actEmail, new User("pavel", "1", "first@mail.ru"));
        assertThat(this.validate.findAll().size(), is(1));

        String action = "update";
        Action.Type act = Action.Type.valueOf(action.toUpperCase());
        this.validate.doAction(act, new User(id, "second", "first login", "first@mail.ru"));
        assertThat(this.validate.findAll().get(0).getName(), is("second"));

        String actionDell = "delete";
        Action.Type actDell = Action.Type.valueOf(actionDell.toUpperCase());
        this.validate.doAction(actDell, new User(id, "second", "first login", "first@mail.ru"));
        assertThat(this.validate.findAll().size(), is(0));
    }
}