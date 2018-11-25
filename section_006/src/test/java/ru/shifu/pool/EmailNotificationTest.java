package ru.shifu.pool;


import org.junit.Test;

/**
 * EmailNotificationTest.
 *
 * @author Pavel Abrikosov (abrikosovp@mail.ru).
 * @version 1.
 * @since 24.11.2018.
 **/
public class EmailNotificationTest {

    @Test
    public void whenAddTen() {
        EmailNotification email = new EmailNotification();
        for (int index = 0; index < 5; index++) {
            email.emailTo(new User("User #" + index, "email #" + index));
        }
        email.close();
    }
}