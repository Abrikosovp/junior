package ru.shifu.pool;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
/**
 * EmailNotification.
 *
 * @author Pavel Abrikosov (abrikosovp@mail.ru).
 * @version 1.
 * @since 24.11.2018.
 **/
public class EmailNotification {

    private ExecutorService pool = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());

    /**
     * Метод через ExecutorService отправляет почту
     * @param user user
     */
    public void emailTo(User user) {
        String suject = String.format("Уведомление %s для %s", user.getUsername(), user.getEmail());
        String body = String.format("Добавить новое сообщение для %s ", user.getUsername());
        pool.execute(() -> send(suject, body, user.getEmail()));
    }

    public void send(String suject, String body, String email) {
        System.out.println(suject);
        System.out.println(body);
    }

    public void close() {
        this.pool.shutdown();
    }
}
