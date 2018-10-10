package ru.shifu.tracker;
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
    public void fillActions() {
        this.actions[0] = new AddItem();
        this.actions[1] = new ShowAllItem();
        this.actions[2] = new EditItem();
        this.actions[3] = new DeleteItem();
        this.actions[4] = new FindByIdItem();
        this.actions[5] = new FindByNameItem();
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
    private class AddItem implements UserAction {
        @Override
        public int key() {
            return 0;
        }

        @Override
        public void execute(Input input, Tracker tracker) {
            System.out.println(" -------- Добавление новой заявки -------- ");
            String name = input.ask(" Введите имя заявки ");
            String desc = input.ask(" Введите описание заявки ");
            Item item = new Item(name, desc);
            tracker.add(item);
            System.out.println(" -------- Новая заявка с getId: " + item.getId() + " созданна. -------- ");
        }

        @Override
        public String info() {
            return String.format("%s. %s", this.key(), "Добавить новую заявку");
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
            System.out.println(" -------- Показать все заявки ---------- ");
            for (Item item:tracker.findAll()) {
                System.out.println("Имя: " + item.getName());
                System.out.println("Описание: " + item.getDescription());
                System.out.println("ID: " + item.getId());

            }
        }

        @Override
        public String info() {
            return String.format("%s. %s", this.key(), "Показать все заявки");
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
            System.out.println(" -------- Редактирование заявки -------- ");
            String id = input.ask(" Введи ID заявки: ");
            Item item = tracker.findById(id);
            if (item != null) {
                System.out.println(" Заявка с ID " + item.getId() + " будет изменена.");
                String name = input.ask(" Введите имя новой заявки: ");
                String desc = input.ask(" Введите описание новой заявки: ");
                Item newItem = new Item(name, desc);
                newItem.setId(item.getId());
                tracker.replace(id, newItem);
                System.out.println(" Заявка с ID изменина");
            } else {
                System.out.println(" Заявка с таким ID не найдена");
            }
        }

        @Override
        public String info() {
            return String.format("%s. %s", this.key(), "Редактировать заявку");
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
            System.out.println(" -------- Удаление заявки -------- ");
            String id = input.ask(" Введите ID заявки: ");
            Item item = tracker.findById(id);
            if (item != null) {
                System.out.println(" Заявка с ID: " + item.getId() + " будет удаленна.");
                tracker.delete(id);
            } else {
                System.out.println(" Заявка с таким ID не найдена");
            }
        }

        @Override
        public String info() {
            return String.format("%s. %s", this.key(), "Удалить заявку");
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
            System.out.println(" -------- Поиск заявки по ID. -------- ");
            String id = input.ask(" Введите ID заявки");
            Item item = tracker.findById(id);
            if (item != null) {
                System.out.println(" Заявка: " + item.getName());
            } else {
                System.out.println(" Заявка с таким ID не найдена");
            }
        }

        @Override
        public String info() {
            return String.format("%s. %s", this.key(), "Поиск заявки по ID");
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
            System.out.println(" -------- Поиск заявок по имени: -------- ");
            String name = input.ask(" Введите имя заявки: ");
            Item[] items = tracker.findByName(name);
            for (Item item : items) {
                if (item != null) {
                    System.out.println("Имя: " + item.getName());
                    System.out.println("Описание: " + item.getDescription());
                    System.out.println("ID: " + item.getId());
                } else {
                    System.out.println(" Заявка с таким именем не найдена");
                }
            }
        }

        @Override
        public String info() {
            return String.format("%s. %s", this.key(), "Поиск заявки по имени");
        }
    }
}
