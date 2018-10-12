package ru.shifu.tracker;
/**
 * Input ввода/вывода данных от пользователя .
 *
 * @author Pavel Abrikosov (abrikosovp@mail.ru).
 * @version 1.
 * @since 09.10.2018.
 **/
public interface Input {
    /**
     * @param question спросить пользователя.
     * @return ответ.
     */
    String ask(String question);

    /**
     * @param question спросить пользователя.
     * @param range граници для ввода.
     * @return
     */

    int ask(String question, int[] range);


}
