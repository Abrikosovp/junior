package ru.shifu.tracker;
/**
 * Внутренний класс ExitProgram = 6
 *
 * Выход из программы.
 * @key Ключ запуска для добавления.
 * @execute Метод действия.
 * @info Выводит описывающую информацию.
 *
 */
 class ExitProgram implements UserAction {

    private StartUI exitProgram;

    public ExitProgram(StartUI exitProgram) {
        this.exitProgram = exitProgram;
    }
    @Override
    public int key() {
        return 6;
    }
    @Override
    public void execute(Input input, Tracker tracker) {
        this.exitProgram.stop();
    }
    @Override
    public String info() {
        return String.format("%s. %s", this.key(), "Exit for program");
    }
}

/**
 * StartUI точка входа в программу, .
 *
 * @author Pavel Abrikosov (abrikosovp@mail.ru).
 * @version 1.
 * @since 10.10.2018.
 **/
public class MenuTracker {
    /**
     * Система ввода и вывода.
     */
    private Input input;
    /**
     * Является хранилищем (выполняет основ действия: хронит, добавляет , редактирует ).
     */
    private Tracker tracker;


    public MenuTracker(Input input, Tracker tracker) {
        this.input = input;
        this.tracker = tracker;
    }

    /**
     * actions - те действия которые описанны в нашей системе.
     */
    private UserAction[] actions = new UserAction[10];

    /**
     * Метод инициализурет ключи для команд в массиве actions.
     * Удобно его использовать для цикла.
     */
    public void fillActions(StartUI ui) {
        this.actions[0] = new AddItem();
        this.actions[1] = new ShowAllItem();
        this.actions[2] = new EditItem();
        this.actions[3] = new DeleteItem();
        this.actions[4] = new FindByIdItem();
        this.actions[5] = new FindByNameItem();
        this.actions[6] = new ExitProgram(ui);
    }
    /**
     * Метод вычисляет длинну массива.
     * @return length.
     */
    public int getActionsLength() {
        return actions.length;
    }
    /**
     * Метод рачитывает граници, для ввода пользователем.
     * @return range.
     */
    public int[] getFullRange() {
        int[] range = new int[this.getActionsLength()];
        for (int i = 0; i != this.getActionsLength(); i++) {
            range[i] = i;
        }
        return range;
    }

    /**
     * Метод выполняет наши действия, которые выбрал пользователь.
     * @param key уникальный ключ.
     */
    public void select(int key) {
        this.actions[key].execute(this.input, this.tracker);
    }

    /**
     * Метод печатает меню.
     */
    public void show() {
        for (UserAction action:this.actions) {
            if (action != null) {
                System.out.println(action.info());
            }
        }
    }
    /**
     * Внутренний класс ADD = 0
     *
     * Добавляет заявку в хранилище!
     * @key Ключ запуска для добавления
     * @execute Метод действия
     * @info Выводит описывающую информацию.
     *
     */
    private static class AddItem implements UserAction {
        @Override
        public int key() {
            return 0;
        }

        @Override
        public void execute(Input input, Tracker tracker) {
            System.out.println(" ------------ Adding new item -------------- ");
            String name = input.ask(" Please, provide item name: ");
            String desc = input.ask(" Please, provide item description: ");
            Item item = new Item(name, desc);
            tracker.add(item);
            System.out.println(" ------------ New Item with Id: " + item.getId());
        }

        @Override
        public String info() {
            return String.format("%s. %s", this.key(), "Add new Item");
        }
    }
    /**
     * Внутренний класс ShowItems = 1
     *
     * Выводит на экран все заявки.
     * @key Ключ запуска для добавления.
     * @execute Метод действия.
     * @info Выводит описывающую информацию  действия.
     *
     */
    private class ShowAllItem implements UserAction {
        @Override
        public int key() {
            return 1;
        }

        @Override
        public void execute(Input input, Tracker tracker) {
            System.out.println(" ------------ All items ------------ ");
            for (Item item:tracker.findAll()) {
                System.out.println(" ------------ Name: " + item.getName());
                System.out.println(" ------------ Description: " + item.getDescription());
                System.out.println(" ------------ ID: " + item.getId());

            }
        }

        @Override
        public String info() {
            return String.format("%s. %s", this.key(), "Show all items");
        }
    }
    /**
     * Внутренний класс EditItem = 2
     *
     * Обновляет заявку в хранилище.
     * @key Ключ запуска для добавления.
     * @execute Метод действия.
     * @info Выводит описывающую информацию.
     *
     */
    private class EditItem implements UserAction {

        @Override
        public int key() {
            return 2;
        }

        @Override
        public void execute(Input input, Tracker tracker) {
            System.out.println(" ------------ Edit item ------------ ");
            String id = input.ask(" Enter id: ");
            Item item = tracker.findById(id);
            if (item != null) {
                System.out.println(" Item with ID: " + item.getId() + " will be changed. ");
                String name = input.ask(" Please, provide item name: ");
                String desc = input.ask(" Please, provide item description: ");
                Item newItem = new Item(name, desc);
                newItem.setId(item.getId());
                tracker.replace(id, newItem);
                System.out.println(" ------------ Edited ------------ ");
            } else {
                System.out.println(" ----- No item with this ID ----- ");
            }
        }

        @Override
        public String info() {
            return String.format("%s. %s", this.key(), "Edit item");
        }
    }
    /**
     * Внутренний класс Delete = 3
     *
     * Удаляет заявку из хранилища, а именно присваивает ей null.
     * @key Ключ запуска для добавления.
     * @execute Метод действия.
     * @info Выводит описывающую информацию.
     *
     */
    private class DeleteItem implements UserAction {

        @Override
        public int key() {
            return 3;
        }

        @Override
        public void execute(Input input, Tracker tracker) {
            System.out.println(" ------------ Delete item ------------ ");
            String id = input.ask(" Enter item id: ");
            Item item = tracker.findById(id);
            if (item != null) {
                System.out.println(" ------------ Deleted ---------------- ");
                tracker.delete(id);
            } else {
                System.out.println(" ------ No item with this ID ------ ");
            }
        }

        @Override
        public String info() {
            return String.format("%s. %s", this.key(), "Delete item");
        }
    }
    /**
     * Внутренний класс FindItemById = 4
     *
     * Ищет заявку по ID.
     * @key Ключ запуска для добавления.
     * @execute Метод действия.
     * @info Выводит описывающую информацию.
     *
     */
    private class FindByIdItem implements UserAction {

        @Override
        public int key() {
            return 4;
        }

        @Override
        public void execute(Input input, Tracker tracker) {
            System.out.println(" -------- Find item by Id -------- ");
            String id = input.ask(" Enter id:");
            Item item = tracker.findById(id);
            if (item != null) {
                System.out.println(" ------------ Item: " + item.getName());
            } else {
                System.out.println(" ----- No item with this ID ----- ");
            }
        }

        @Override
        public String info() {
            return String.format("%s. %s", this.key(), "Find item by Id");
        }
    }
    /**
     * Внутренний класс FindItemByName = 5
     *
     * Ищет заявку по строке имени.
     * @key Ключ запуска для добавления.
     * @execute Метод действия.
     * @info Выводит описывающую информацию.
     *
     */
    private class FindByNameItem implements UserAction {
        @Override
        public int key() {
            return 5;
        }

        @Override
        public void execute(Input input, Tracker tracker) {
            System.out.println(" -------- Find items with name -------- ");
            String name = input.ask(" Enter name: ");
            Item[] items = tracker.findByName(name);
            for (Item item : items) {
                if (item != null) {
                    System.out.println(" ------------ Name: " + item.getName());
                    System.out.println(" ------------ Описание: " + item.getDescription());
                    System.out.println(" ------------ ID: " + item.getId());
                } else {
                    System.out.println(" ----- No item with this name ----- ");
                }
            }
        }

        @Override
        public String info() {
            return String.format("%s. %s", this.key(), "Find items by name");
        }
    }


}
