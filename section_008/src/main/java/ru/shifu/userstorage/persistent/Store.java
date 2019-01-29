package ru.shifu.userstorage.persistent;


import java.util.List;
/**
 * Storage of users interface.
 * Persistent layout.
 *
 * @author Pavel Abrikosov (abrikosovp@mail.ru)
 * @version 0.2$
 * @since 0.1
 * 18.01.2019
 */
public interface Store {

    boolean add(User user);

    boolean update(User user);

    boolean delete(User user);

    List<User> findAll();

    User findById(String id);

    boolean validate(User user);
}
