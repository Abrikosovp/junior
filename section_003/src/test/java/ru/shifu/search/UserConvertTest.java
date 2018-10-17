package ru.shifu.search;
import org.junit.Test;

import java.util.*;

import static junit.framework.TestCase.assertTrue;
/**
 * UserConvertTest.
 *
 * @author Pavel Abrikosov (abrikosovp@mail.ru).
 * @version 1.
 * @since 0.1.
 */
public class UserConvertTest {
    /**
     * Тест конвертирует List<User> в HashMap.
     */
    @Test
    public void wenListThenMap() {
        UserConvert user = new UserConvert();
        List<User> list = Arrays.asList(
                new User(22, "Паша", "Msc"),
                new User(33, "Антон", "Минск"));
        Map<Integer, User> result = user.process(list);
        HashMap<Integer, User> expected = new HashMap<>();
        expected.put(22, new User(22, "Паша", "Msc"));
        expected.put(33, new User(33, "Антон", "Минск"));
        assertTrue(result.equals(expected));
    }
}
