package ru.shifu.tracker;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;
/**
 * StartUITest .
 *
 * @author Pavel Abrikosov (abrikosovp@mail.ru).
 * @version 1.
 * @since 10.10.2018.
 **/
public class StartUITest {
    /**
     * Массив байт, который мы можем проверить в нашем тесте.
     */
    private final ByteArrayOutputStream out = new ByteArrayOutputStream();
    /**
     * Получаем ссылку на стандартный вывод в консоль.
     */
    private final PrintStream stdout = System.out;
    /**
     * Класс для вывода в консоль и заменить ее выводом в память.
     */
    @Before
    public void loadOutput() {
        System.setOut(new PrintStream(this.out));
    }
    /**
     * Возвращаем обратно стандартный вывод в консоль.
     */
    @After
    public void backOutput() {
        System.setOut(this.stdout);
    }

    String menu =
              "0. Добавить новую заявку\n"
            + "1. Показать все заявки\n"
            + "2. Редактировать заявку\n"
            + "3. Удалить заявку\n"
            + "4. Поиск заявки по ID\n"
            + "5. Поиск заявки по имени";

    /**
     * Тест - добавление новой заявки.
     */
    @Test
    public void whenUserCreateItemItemThenTrackerHasNewItemWithSameName() {
        Tracker tracker = new Tracker();     // создаём Tracker
        Input input = new StubInput(new String[]{"0", "test name", "desc", "y"});   //создаём StubInput с последовательностью действий
        new StartUI(input, tracker).init();     //   создаём StartUI и вызываем метод init()
        assertThat(tracker.findAll()[0].getName(), is("test name")); // проверяем, что нулевой элемент массива в трекере содержит имя, введённое при эмуляции.
    }
    /**
     * Тест - показать все заявки.
     */
    @Test
    public void whenShowAllThenTrackerHasUpdatedValue() {
    Tracker tracker = new Tracker();
    Item item = tracker.add(new Item("test name", "desc"));
    Input input = new StubInput(new String[] {"1", "y"});
    String result = new StringBuilder()
            .append(menu)
            .append(System.lineSeparator())
            .append(" -------- Показать все заявки ---------- ")
            .append(System.lineSeparator())
            .append("Имя: " + item.getName())
            .append(System.lineSeparator())
            .append("Описание: " + item.getDescription())
            .append(System.lineSeparator())
            .append("ID: " + item.getId())
            .append(System.lineSeparator())
            .toString();

    new StartUI(input, tracker).init();
    assertThat(new String(this.out.toByteArray()), is(result));
    }
    /**
     * Тест - редактирование заявки
     */
    @Test
    public void whenEditThenTrackerHasUpdatedValue() {
        Tracker tracker = new Tracker();
        Item item = tracker.add(new Item("test name", "desc"));
        Input input = new StubInput(new String[]{"2", item.getId(), "test replace", "заменили заявку", "y"});
        String result = new StringBuilder()
                .append(menu)
                .append(System.lineSeparator())
                .append(" -------- Редактирование заявки -------- ")
                .append(System.lineSeparator())
                .append(" Заявка с ID " + item.getId() + " будет изменена.")
                .append(System.lineSeparator())
                .append(" Заявка с ID изменина")
                .append(System.lineSeparator()).toString();

        new StartUI(input, tracker).init();
        assertThat(new String(this.out.toByteArray()), is(result));
    }
    /**
     * Тест - удаления заявки.
     */
    @Test
    public void wheUserDeleteItemThenTrackerHasNoItemWithThatId() {
        Tracker tracker = new Tracker();
        Item item = tracker.add(new Item("test name", "desc"));
        tracker.add(new Item("other name", "other desc"));
        Input input = new StubInput(new String[]{"3", item.getId(), "y"});
        String result = new StringBuilder()
                .append(menu)
                .append(System.lineSeparator())
                .append(" -------- Удаление заявки -------- ")
                .append(System.lineSeparator())
                .append(" Заявка с ID: " + item.getId() + " будет удаленна.")
                .append(System.lineSeparator())
                .toString();

        new StartUI(input, tracker).init();
        assertThat(new String(this.out.toByteArray()), is(result));
    }
    /**
     * Тест - найти по ID.
     */
    @Test
    public void whenFindByIDThenTrackerHasUpdatedValue() {
        Tracker tracker = new Tracker();
        Item item = tracker.add(new Item("test name", "desc"));
        Input input = new StubInput(new String[]{"4", item.getId(),  "y"});
        String string = new StringBuilder()
                .append(menu)
                .append(System.lineSeparator())
                .append(" -------- Поиск заявки по ID. -------- ")
                .append(System.lineSeparator())
                .append(" Заявка: " + item.getName())
                .append(System.lineSeparator())
                .toString();

        new StartUI(input, tracker).init();
        assertThat(new String(this.out.toByteArray()), is(string));
    }
    /**
     * Тест - найти по имени.
     */
    @Test
    public void whenFindByNameThenTrackerHasUpdatedValue() {
        Tracker tracker = new Tracker();
        Item item = tracker.add(new Item("test name", "desc"));
        Input input = new StubInput(new String[]{"5", item.getName(), "y"});
        String result = new StringBuilder()
                .append(menu).append(System.lineSeparator())
                .append(" -------- Поиск заявок по имени: -------- ")
                .append(System.lineSeparator())
                .append("Имя: " + item.getName())
                .append(System.lineSeparator())
                .append("Описание: " + item.getDescription())
                .append(System.lineSeparator())
                .append("ID: " + item.getId())
                .append(System.lineSeparator())
                .toString();

        new StartUI(input, tracker).init();
        assertThat(new String(out.toByteArray()), is(result));
    }
}
