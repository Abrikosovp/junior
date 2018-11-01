package ru.shifu.map;

import java.util.Calendar;
import java.util.Objects;

/**
 * User.
 *
 * @author Pavel Abrikosov (abrikosovp@mail.ru).
 * @version 1.
 * @since 2.11.2018.
 **/
public class User {
    private String name;
    private int children;
    private Calendar birthday;

    public User(String name, int children, Calendar bersday) {
        this.name = name;
        this.children = children;
        this.birthday = bersday;
    }

    @Override
    public boolean equals(Object o) {

        if (this == o) {
            return true;
        }

        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        User user = (User) o;
        return Objects.equals(children, user.children)
                && Objects.equals(name, user.name)
                && Objects.equals(birthday, user.birthday);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, children, birthday);
    }
}
