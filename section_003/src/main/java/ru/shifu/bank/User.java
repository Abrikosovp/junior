package ru.shifu.bank;

import java.util.Objects;
/**
 * User.
 *
 * @author Pavel Abrikosov (abrikosovp@mail.ru).
 * @version 1.
 * @since 25.10.2018.
 **/
public class User implements Comparable<User> {
    private String name;
    private String passport;

    public User(String name, String passport) {
        this.name = name;
        this.passport = passport;
    }

    public String getName() {
        return name;
    }

    public String getPassport() {
        return passport;
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
        return Objects.equals(name, user.name)
                && Objects.equals(passport, user.passport);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, passport);
    }

    @Override
    public int compareTo(User o) {
        int result = this.name.compareTo(o.getName());
        return result != 0 ? result : this.passport.compareTo(o.getPassport());
    }
}
