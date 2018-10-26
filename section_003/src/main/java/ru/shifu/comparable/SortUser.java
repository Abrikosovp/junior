package ru.shifu.comparable;
import java.util.*;
/**
 * SortUser.
 *
 * @author Pavel Abrikosov (abrikosovp@mail.ru).
 * @version 1.
 * @since 14.10.2018.
 **/
public class SortUser {
    /**
     * Метод в TreeSet сортирует через Comparable по age
     *
     * @param list лист с User
     * @return отсортерованный TreeSet по age
     */
    public Set<User> sort(List<User> list) {
        return new TreeSet<>(list);
    }

    /**
     * Метод сортирует по name.length
     * @param list список с Users
     * @return list - отсортированный.
     */
    public List<User> sortNameLength(List<User> list) {
        list.sort((o1, o2) -> {
            int result = 0;
            if (o1.getName().length() > o2.getName().length()) {
                result = 1;
            } else if (o1.getName().length() < o2.getName().length()) {
                result = -1;
            }
            return result;
        });
    return list;
    }

    /**
     * Метод сортирует по обеим полям,
     * сначала сортировка по имени в лексикографическом порядке,
     * потом по возрасту.
     * @param list список users
     * @return list - отсортированный.
     */
    public List<User> sortByAllFields(List<User> list) {
        list.sort((o1, o2) ->  {
                int value = o1.getName().compareTo(o2.getName());
                return value != 0 ? value : Integer.compare(o1.getAge(), o2.getAge());
        });
        return  list;
    }
}
