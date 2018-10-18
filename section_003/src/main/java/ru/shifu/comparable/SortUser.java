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
     * @param list лист с User
     * @return отсортерованный TreeSet по age
     */
    public Set<User> sort(List<User> list) {
       return new TreeSet<>(list);
    }
}
