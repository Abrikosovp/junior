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

   private boolean exit = false;

    public StartUI(Input input, Tracker tracker) {
        this.input = input;
        this.tracker = tracker;
    }
    /**
     * Константа меню для добавления новой заявки.
     */
    private static final String ADD = "0";
    /**
     * Константа меню для получения списка всех заявок.
     */
    private static final String FINDALL = "1";
    /**
     * Константа меню для редактирования заявки.
     */
    private static final String REPLACE = "2";
    /**
     * Константа меню для удаления заявки.
     */
    private static final String DELETE = "3";
    /**
     * Константа меню для поиска заявки по ID.
     */
    private static final String FINDBYID = "4";
    /**
     * Константа меню для поиска заявок по имени.
     */
    private static final String FINDBYNAME = "5";
    /**
     * Константа для выхода из цикла.
     */
    private static final String EXIT = "6";

    /**
     * Основой цикл программы.
     */
    public void init() {
        while (!exit) {
            this.showMenu();
            String answer = this.input.ask("Введите пункт меню: ");
            if (ADD.equals(answer)) {
                this.createItem();
            } else if (FINDALL.equals(answer)) {
                this.showAll();
            } else if (REPLACE.equals(answer)) {
                this.edit();
            } else if (DELETE.equals(answer)) {
                this.delete();
            } else if (FINDBYNAME.equals(answer)) {
                this.findByName();
            } else if (FINDBYID.equals(answer)) {
                this.findByID();
            } else if (EXIT.equals(answer)) {
                this.setExit();
            }
        }
    }

    /**
     * Меню с заявками.
     */
    private void showMenu() {
        System.out.println("Меню. \n"
                + "0. Add new Item \n"
                + "1. Show all items \n"
                + "2. Edit item \n"
                + "3. Delete item \n"
                + "4. Find items by name \n"
                + "5. Find item by id \n"
                + "6. Exit program \n"
                + "Select: \n");
    }

    /**
     * Добавление новой заявки.
     */
    private void createItem() {
        System.out.println(" -------- Добавление новой заявки -------- ");
        String name = this.input.ask(" Введите имя заявки ");
        String desc = this.input.ask(" Введите описание заявки ");
        Item item = new Item(name, desc);
        this.tracker.add(item);
        System.out.println(" -------- Новая заявка с getId: " + item.getId() + " созданна. -------- ");
    }

    /**
     * Показать все заявки.
     */
    private void showAll() {
        System.out.println(" -------- Показать все заявки ---------- ");
        for (Item item:this.tracker.findAll()) {
            System.out.println("Имя: " + item.getName() + " Описание: " + item.getDescription() + " id: " + item.getId());
        }
    }

    /**
     * Редактирование заявки
     */
    private void edit() {
        System.out.println(" -------- Редактирование заявки -------- ");
        String id = this.input.ask(" Введи ID заявки: ");
        Item item = this.tracker.findById(id);
        if (item != null) {
            System.out.println(" Заявка с ID " + item.getId() + " будет изменена. \n");
            String name = this.input.ask(" Введите имя новой заявки: ");
            String desc = this.input.ask(" Введите описание новой заявки: ");
            Item newItem = new Item(name, desc);
            newItem.setId(item.getId());
            this.tracker.replace(id, newItem);
            System.out.println(" Заявка с ID изменина");
        } else {
            System.out.println(" Заявка с таким ID не найдена");
        }
    }

    /**
     * Удаления заявки.
     */
    private void delete() {
        System.out.println(" -------- Удаление заявки -------- ");
        String id = this.input.ask(" Введите ID заявки: ");
        Item item = this.tracker.findById(id);
        if (item != null) {
            System.out.println(" Заявка с ID: " + item.getId() + " будет удаленна.");
            this.tracker.delete(id);
        } else {
            System.out.println(" Заявка с таким ID не найдена");
        }
    }

    /**
     * Найти по ID.
     */
    private void findByID() {
        System.out.println(" -------- Поиск заявки по ID. -------- ");
        String id = this.input.ask(" Введите ID заявки");
        Item item = this.tracker.findById(id);
        if (item != null) {
            System.out.println(" Заявка: " + item.getName());
        } else {
            System.out.println(" Заявка с таким ID не найдена");
        }
    }

    /**
     * Найти по имени.
     */
    private void findByName() {
        System.out.println(" -------- Поиск заявок по имени: -------- ");
        String name = this.input.ask(" Введите имя заявки: ");
        Item[] items = this.tracker.findByName(name);
            for (Item item : items) {
                if (item != null) {
                System.out.println(" Имя: " + item.getName()
                        + " Описание: " + item.getDescription()
                        + " ID: " + item.getId());
            } else {
                    System.out.println(" Заявка с таким именем не найдена");
                }
            }
    }

    /**
     * Выход из программы.
     */
    private void setExit() {
        System.out.println(" -------- Exit for program: --------");
        this.exit = true;
    }

    public static void main(String[] args) {
        new StartUI(new ConsoleInput(), new Tracker()).init();
    }
}
