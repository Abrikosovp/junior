package ru.shifu.comparable;
import org.junit.Test;

import java.util.*;

import static org.junit.Assert.assertEquals;
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
    /**
     * Тест проверяет что метод отсортировал по name.length
     */
    @Test
    public void whenListBySortToLineName() {
      User u2 = new User("Антон", 20);
      User u3 = new User("Александе", 33);
      User u1 = new User("Яр", 12);
        SortUser sort = new SortUser();
        List<User> list = Arrays.asList(u3, u2, u1);
        List<User> expect = Arrays.asList(u1, u2, u3);
        List<User> result = sort.sortNameLength(list);
        assertEquals(expect, result);
    }
    /**
     * Тест проверяет что метод отсортировал по побоим полям,
     * сначала сортировка по имени в лексикографическом порядке,
     * потом по возрасту.
     */
    @Test
    public void whenListBySortToNameButAge() {
        User u1 = new User("Александр", 6);
        User u2 = new User("Александр", 33);
        User u3 = new User("Александр", 44);
        User u4 = new User("Дима", 2);
        User u5 = new User("Дима", 20);
        User u6 = new User("Макс", 20);
        User u7 = new User("Яр", 12);
        SortUser sort = new SortUser();
        List<User> list = Arrays.asList(u3, u7, u4, u6, u2, u1, u5);
        List<User> expect = Arrays.asList(u1, u2, u3, u4, u5, u6, u7);
        List<User> result = sort.sortByAllFields(list);
        assertEquals(expect, result);
    }
}
