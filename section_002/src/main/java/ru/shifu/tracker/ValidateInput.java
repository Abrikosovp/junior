package ru.shifu.tracker;

/**
 * ValidateInput ввода/вывода данных от пользователя .
 *
 * @author Pavel Abrikosov (abrikosovp@mail.ru).
 * @version 1.
 * @since 12.10.2018.
 **/
public class ValidateInput implements Input {
    /**
     * поле расширяет ValidateInput, можем передать ConsoleInput или StubInput
     */
    private Input input;

    public ValidateInput(Input input) {
        this.input = input;
    }
    /**
     * Метод позволяет задать вопрос и получить ответ с клавиатуры.
     * @param question спросить пользователя.
     * @return ответ который пользователь введет с клавиатуры.
     */
    public String ask(String question) {
        return this.input.ask(question);
    }

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
                key = this.input.ask(question, range);
                invalid = false;
            } catch (MenuOutExeption moe) {
                System.out.println("Please select key from menu. ");
            } catch (NumberFormatException nfe) {
                System.out.println("Please enter validate data again. ");
            }
        } while (invalid);
        return key;
    }
}
