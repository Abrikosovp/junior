package ru.shifu.comparable;
import org.junit.Test;

import java.util.*;
import static org.junit.Assert.assertTrue;
import java.util.Set;
/**
 * SortUserTest.
 *
 * @author Pavel Abrikosov (abrikosovp@mail.ru).
 * @version 1.
 * @since 14.10.2018.
 **/
public class SortUserTest {
    /**
     * Тест проверяет что метод отсортировал по age
     */
    @Test
    public void whenListBySortComparableTreeSet() {
        SortUser user = new SortUser();
        List<User> list = Arrays.asList(
                new User("Павел", 27),
                new User("Антон", 20),
                new User("Аня", 16),
                new User("Федор", 5));
        Set<User> result = user.sort(list);
        TreeSet<User> expected = new TreeSet<>();
        expected.add(new User("Федор", 5));
        expected.add(new User("Аня", 16));
        expected.add(new User("Антон", 20));
        expected.add(new User("Павел", 27));
        assertTrue(result.equals(expected));
    }
}
