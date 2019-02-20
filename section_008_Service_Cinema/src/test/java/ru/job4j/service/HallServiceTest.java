package ru.job4j.service;

import org.junit.Assert;
import org.junit.Test;
/**
 * Halls By Id test.
 *
 * @author Pavel Abrikosov (abrikosovp@mail.ru).
 * @version 1.0.
 * @since 20.02.19
 */
public class HallServiceTest {

    @Test()
    public void thenHallsByIdReturnException() {
        Service service = HallService.getInstance();
        try {
            service.getHallsById(55);
        } catch (IllegalArgumentException e) {
            Assert.assertNotEquals("", e.getMessage());
        }
    }
}