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

    /**
     * Метод позволяет задать вопрос и получить ответ с клавиатуры.
     * @param question спросить пользователя.
     * @param range граници которые пользователь должен ввеси.
     * @return  ответ который пользователь введет с клавиатуры.
     */
    @Override
    public int ask(String question, int[] range) {
        boolean exist = false;
        int key = Integer.valueOf(this.ask(question));
        for (int value : range) {
            if (value == key) {
                exist = true;
                break;
            }
        }
        if (exist) {
            return key;
        } else {
            throw new MenuOutExeption("Outside the boundaries of the range");
        }
    }
}
