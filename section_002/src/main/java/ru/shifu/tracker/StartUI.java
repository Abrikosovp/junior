package ru.shifu.tracker;
/**
 * StartUI точка входа в программу, .
 *
 * @author Pavel Abrikosov (abrikosovp@mail.ru).
 * @version 1.
 * @since 09.10.2018.
 **/
public class StartUI {
    /**
     * Получения данных от пользователя.
     */
    private Input input;
    /**
     * Хранилище заявок.
     */
    private Tracker tracker;


    public StartUI(Input input, Tracker tracker) {
        this.input = input;
        this.tracker = tracker;
    }

    /**
     * Константа для выхода из цикла.
     */
    private  boolean run = false;

    /**
     * Основой цикл программы.
     */
    public void init() {
        MenuTracker menu = new MenuTracker(this.input, this.tracker);
        menu.fillActions(this);

         while (!run) {
             System.out.printf("%s%s%s%s", "Menu.", System.lineSeparator(),
                            "Select a menu item :", System.lineSeparator());
             menu.show();
             menu.select(input.ask("Select: ", menu.getFullRange()));
         }
    }

    /**
     * Метод способствует выходу из программы
     */
    public void stop() {
        this.run = true;
    }

    public static void main(String[] args) {
        new StartUI(new ValidateInput(new ConsoleInput()), new Tracker()).init();
    }
}
