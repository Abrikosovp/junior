package ru.shifu.map;

import java.util.Calendar;
/**
 * UserWithoutEqualsAndHash.
 *
 * @author Pavel Abrikosov (abrikosovp@mail.ru).
 * @version 1.
 * @since 2.11.2018.
 **/
public class UserWithoutEqualsAndHash {
    private String name;
    private int children;
    private Calendar birthday;

    public UserWithoutEqualsAndHash(String name, int children, Calendar birthday) {
        this.name = name;
        this.children = children;
        this.birthday = birthday;
    }
}
