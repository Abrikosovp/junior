package ru.shifu.tracker;

import java.util.Scanner;
/**
 * ConsoleInput ввода/вывода данных от пользователя .
 *
 * @author Pavel Abrikosov (abrikosovp@mail.ru).
 * @version 1.
 * @since 09.10.2018.
 **/
public class ConsoleInput implements Input {
    /**
     * Класс ввода вывода/вывода.
     */
    private Scanner scanner = new Scanner(System.in);

    /**
     * Метод позволяет задать вопрос и получить ответ с клавиатуры.
     * @param question спросить пользователя.
     * @return ответ который пользователь введет с клавиатуры.
     */
    @Override
    public String ask(String question) {
        System.out.println(question);
        return scanner.nextLine();
    }
}
