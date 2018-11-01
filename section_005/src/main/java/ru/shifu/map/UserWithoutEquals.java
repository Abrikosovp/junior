package ru.shifu.map;

import java.util.Calendar;
import java.util.Objects;
/**
 * UserWithoutEquals.
 *
 * @author Pavel Abrikosov (abrikosovp@mail.ru).
 * @version 1.
 * @since 2.11.2018.
 **/
public class UserWithoutEquals {
    private String name;
    private int children;
    private Calendar birthday;

    public UserWithoutEquals(String name, int children, Calendar birthday) {
        this.name = name;
        this.children = children;
        this.birthday = birthday;
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, children, birthday);
    }
}
