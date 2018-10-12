package ru.shifu.tracker;
/**
 * ValidateImput ввода/вывода данных от пользователя .
 *
 * @author Pavel Abrikosov (abrikosovp@mail.ru).
 * @version 1.
 * @since 12.10.2018.
 **/
public class ValidateImput extends ConsoleInput {
    /**
     * Метод позволяет задать вопрос и получить ответ с клавиатуры.
     * @param question спросить пользователя.
     * @param range граници которые пользователь должен ввеси.
     * @return  ответ который пользователь введет с клавиатуры.
     */
    @Override
    public int ask(String question, int[] range) {
        boolean invalid = true;
        int key = -1;
        do {
            try {
                key = super.ask(question, range);
                invalid = false;
            } catch (MenuOutExeption moe) {
                System.out.println("Please, select an index within the bounds of the array.");
            } catch (NumberFormatException nfe) {
                System.out.println("Please, enter valid details.");
            }
        } while (invalid);
        return key;
    }
}
