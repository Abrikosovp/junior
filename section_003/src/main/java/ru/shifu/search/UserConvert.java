package ru.shifu.search;

import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

/**
 * UserConvert
 *
 * @author Pavel Abrikosov (abrikosovp@mail.ru).
 * @version 1.
 * @since 17.10.2018.
 **/
public class UserConvert {
    /**
     * Метод конвертирует List<User> в HashMap
     * @param list list<User>
     * @return HashMap
     */
    public HashMap<Integer, User> process(List<User> list) {
        return list.stream().collect(Collectors.toMap(User::getId, user -> user, (acc, value) -> value, HashMap::new));
    }
}
