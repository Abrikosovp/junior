package ru.shifu.store;

import java.util.List;
/**
 * StoreTest.
 *
 * @author Pavel Abrikosov (abrikosovp@mail.ru).
 * @version 1.
 * @since 6.11.2018.
 **/
public class Store {
    /**
     * Метод считетчик изменений.
     * @param previous до.
     * @param current после.
     * @return result.
     */
    Info diff(List<User> previous, List<User> current) {
        Info info = new Info(previous, current);
        info.changeCheck();
     return info;
    }

    /**
     * Класс User.
     */
    static class User {
        int id;
        String name;

        public User(int id, String name) {
            this.id = id;
            this.name = name;
        }
    }
}
