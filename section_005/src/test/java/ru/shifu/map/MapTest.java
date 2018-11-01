package ru.shifu.map;
import org.junit.Test;
import ru.shifu.generic.Role;

import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Map;
/**
 * MapTest.
 *
 * @author Pavel Abrikosov (abrikosovp@mail.ru).
 * @version 1.
 * @since 2.11.2018.
 **/
public class MapTest {
    /**
     * equals и hashCode переопределены.
     * Тест проверяе что у обьектов одинаковые hashCode и == по equals
     */
    @Test
    public void whenUser() {
        Map<User, Object> map = new HashMap<>();
        User first = new User("Павел", 2, new GregorianCalendar(20, 10, 1990));
        User second = new User("Павел", 2, new GregorianCalendar(20, 10, 1990));

        System.out.println(first.hashCode());
        System.out.println(second.hashCode());
        System.out.println(first.equals(second));
        map.put(first, new Object());
        map.put(second, new Object());
        System.out.println(map);

        //тест для equals
        UserWithoutEquals one = new UserWithoutEquals("Сеня", 11, new GregorianCalendar(2, 1, 1990));
        first.equals(one);
        first.equals(first);
        first.equals(null);
    }

    /**
     * Переопределен только hashCode.
     * Тест проверяе что у обьектов одинаковые hashCode и != по equals
     */
    @Test
    public void whenUserWithoutEquals() {
        Map<UserWithoutEquals, Object> map = new HashMap<>();
        UserWithoutEquals first = new UserWithoutEquals("Павел", 2, new GregorianCalendar(20, 10, 1990));
        UserWithoutEquals second = new UserWithoutEquals("Павел", 2, new GregorianCalendar(20, 10, 1990));

        System.out.println(first.hashCode());
        System.out.println(second.hashCode());
        System.out.println(first.equals(second));
        map.put(first, new Object());
        map.put(second, new Object());
        System.out.println(map);
    }

    /**
     * equals и hashCode не переопределены.
     * Тест проверяе что разные hashCode и != по equals
     */
    @Test
    public void whenUserWithoutEqualsAndHashCode() {
        Map<UserWithoutEqualsAndHash, Object> map = new HashMap<>();
        UserWithoutEqualsAndHash first = new UserWithoutEqualsAndHash("Павел", 2, new GregorianCalendar(20, 10, 1990));
        UserWithoutEqualsAndHash second = new UserWithoutEqualsAndHash("Павел", 2, new GregorianCalendar(20, 10, 1990));
        System.out.println(first.hashCode());
        System.out.println(second.hashCode());
        System.out.println(first.equals(second));
        map.put(first, new Object());
        map.put(second, new Object());
        System.out.println(map);
    }
}
