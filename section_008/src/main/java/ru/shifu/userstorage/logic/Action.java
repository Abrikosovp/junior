package ru.shifu.userstorage.logic;
/**
 * Actions interface.
 *
 * @author Pavel Abrikosov (abrikosovp@mail.ru)
 * @version 0.1$
 * @since 0.1
 * 18.01.2019
 */
public interface Action {
    enum Type {
        ADD,
        UPDATE,
        DELETE
    }
}