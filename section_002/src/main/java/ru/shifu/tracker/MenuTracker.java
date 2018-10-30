package ru.shifu.tracker;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.function.Consumer;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * Внутренний класс ExitProgram = 6
 *
 * Выход из программы.
 * @execute Метод действия.
 *
 */
 class ExitProgram extends BaseAction {

    private StartUI exitProgram;

    public ExitProgram(String name, int key, StartUI exitProgram) {
        super(name, key);
        this.exitProgram = exitProgram;
    }

    @Override
    public void execute(Input input, Tracker tracker) {
        this.exitProgram.stop();
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
    private ArrayList<UserAction> actions = new ArrayList<>();
    /**
     * Поле ответсвенне за пронумерование меню (key()) для вывода на экран.
     */
    private int menuNumber = 0;

    /**
     * Метод инициализурет ключи для команд в массиве actions.
     * Удобно его использовать для цикла.
     * @param ui
     */
    public void fillActions(StartUI ui) {
        this.actions.add(new AddItem("Add new Item", menuNumber++));
        this.actions.add(new ShowAllItem("Show all items", menuNumber++));
        this.actions.add(new EditItem("Edit item", menuNumber++));
        this.actions.add(new DeleteItem("Delete item", menuNumber++));
        this.actions.add(new FindByIdItem("Find item by Id", menuNumber++));
        this.actions.add(new FindByNameItem("Find items by name", menuNumber++));
        this.actions.add(new ExitProgram("Exit for program", menuNumber, ui));
    }

    /**
     * Метод вычисляет длинну массива.
     *
     * @return length.
     */
    public int getActionsLength() {
        return actions.size();
    }

    /**
     * Метод рачитывает граници, для ввода пользователем.
     *
     * @return range.
     */
    public List<Integer> getFullRange() {
    return IntStream.rangeClosed(0, getActionsLength())
            .boxed()
            .collect(Collectors.toList());
    }

    /**
     * Метод выполняет наши действия, которые выбрал пользователь.
     *
     * @param key уникальный ключ.
     */
    public void select(int key) {
        this.actions.get(key).execute(this.input, this.tracker);
    }

    /**
     * Метод печатает меню.
     */
    public void show(Consumer<String> consumer) {
        this.actions.stream()
                .filter(Objects::nonNull)
                .map(UserAction::info)
                .forEach(consumer);
    }

    /**
     * Внутренний класс ADD = 0
     *
     * Добавляет заявку в хранилище!
     * @execute Метод действия
     */
    private static class AddItem extends BaseAction {

        public AddItem(String name, int key) {
            super(name, key);
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
    }

    /**
     * Внутренний класс ShowItems = 1
     *
     * Выводит на экран все заявки.
     * @execute Метод действия.
     */
    private class ShowAllItem extends BaseAction {

        public ShowAllItem(String name, int key) {
            super(name, key);
        }

        @Override
        public void execute(Input input, Tracker tracker) {
            System.out.println(" ------------ All items ------------ ");
            for (Item item : tracker.findAll()) {
                System.out.println(" ------------ Name: " + item.getName());
                System.out.println(" ------------ Description: " + item.getDescription());
                System.out.println(" ------------ ID: " + item.getId());

            }
        }
    }

    /**
     * Внутренний класс EditItem = 2
     *
     * Обновляет заявку в хранилище.
     * @execute Метод действия.
     */
    private class EditItem extends BaseAction {

        EditItem(String name, int key) {
            super(name, key);
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
    }

    /**
     * Внутренний класс Delete = 3
     * <
     * Удаляет заявку из хранилища, а именно присваивает ей null.
     * @execute Метод действия.
     */
    private class DeleteItem extends BaseAction {

        public DeleteItem(String name, int key) {
            super(name, key);
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
    }

    /**
     * Внутренний класс FindItemById = 4
     *
     * Ищет заявку по ID.
     * @execute Метод действия.
     */
    private class FindByIdItem extends BaseAction {


        public FindByIdItem(String name, int key) {
            super(name, key);
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
    }

    /**
     * Внутренний класс FindItemByName = 5
     *
     * Ищет заявку по строке имени.
     * @execute Метод действия.
     */
    private class FindByNameItem extends BaseAction {

        public FindByNameItem(String name, int key) {
            super(name, key);
        }

        @Override
        public void execute(Input input, Tracker tracker) {
            System.out.println(" -------- Find items with name -------- ");
            String name = input.ask(" Enter name: ");
            ArrayList<Item> items = tracker.findByName(name);
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
    }
}
