package ru.shifu.tracker;

import org.hamcrest.core.Is;
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
    /**
     * Перенос строки
     */
    private  final String sepor = System.lineSeparator();

    /**
     * Тест - добавление новой заявки.
     */
    @Test
    public void whenUserCreateItemItemThenTrackerHasNewItemWithSameName() {
        Tracker tracker = new Tracker();     // создаём Tracker
        Input input = new StubInput(new String[]{"0", "test name", "desc", "6"});   //создаём StubInput с последовательностью действий
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
    Input input = new StubInput(new String[] {"1", "6"});
    StringBuilder result = new StringBuilder();
      result.append(String.format(" ------------ All items ------------ %s", sepor))
            .append(String.format(" ------------ Name: %s%s", item.getName(), sepor))
            .append(String.format(" ------------ Description: %s%s",item.getDescription(), sepor))
            .append(String.format(" ------------ ID: %s%s", item.getId(), sepor))
            .toString();

    new StartUI(input, tracker).init();
    assertThat(new String(this.out.toByteArray()), Is.is(this.toString(result)));
    }
    /**
     * Тест - редактирование заявки
     */
    @Test
    public void whenEditThenTrackerHasUpdatedValue() {
        Tracker tracker = new Tracker();
        Item item = tracker.add(new Item("test name", "desc"));
        Input input = new StubInput(new String[]{"2", item.getId(), "test replace", "заменили заявку", "6"});
        StringBuilder result = new StringBuilder();
          result.append(String.format(" ------------ Edit item ------------ %s", sepor))
                .append(String.format(" Item with ID: %s will be changed. %s", item.getId(), sepor))
                .append(String.format(" ------------ Edited ------------ %s", sepor))
                .toString();

        new StartUI(input, tracker).init();
        assertThat(new String(this.out.toByteArray()), Is.is(this.toString(result)));
    }
    /**
     * Тест - удаления заявки.
     */
    @Test
    public void wheUserDeleteItemThenTrackerHasNoItemWithThatId() {
        Tracker tracker = new Tracker();
        Item item = tracker.add(new Item("test name", "desc"));
        tracker.add(new Item("other name", "other desc"));
        Input input = new StubInput(new String[]{"3", item.getId(), "6"});
        StringBuilder result = new StringBuilder();
          result.append(String.format(" ------------ Delete item ------------ %s", sepor))
                .append(String.format(" ------------ Deleted ---------------- %s", sepor))
                .toString();

        new StartUI(input, tracker).init();
        assertThat(new String(this.out.toByteArray()), Is.is(this.toString(result)));
    }
    /**
     * Тест - найти по ID.
     */
    @Test
    public void whenFindByIDThenTrackerHasUpdatedValue() {
        Tracker tracker = new Tracker();
        Item item = tracker.add(new Item("test name", "desc"));
        Input input = new StubInput(new String[]{"4", item.getId(),  "6"});
        StringBuilder result = new StringBuilder();
          result.append(String.format(" -------- Find item by Id -------- %s", sepor))
                .append(String.format(" ------------ Item: %s%S", item.getName(), sepor))
                .toString();

        new StartUI(input, tracker).init();
        assertThat(new String(this.out.toByteArray()), Is.is(this.toString(result)));
    }
    /**
     * Тест - найти по имени.
     */
    @Test
    public void whenFindByNameThenTrackerHasUpdatedValue() {
        Tracker tracker = new Tracker();
        Item item = tracker.add(new Item("test name", "desc"));
        Input input = new StubInput(new String[]{"5", item.getName(), "6"});
        StringBuilder result = new StringBuilder();
          result.append(String.format(" -------- Find items with name -------- %s", sepor))
                .append(String.format(" ------------ Name: %s%s", item.getName(), sepor))
                .append(String.format(" ------------ Описание: %s%s", item.getDescription(), sepor))
                .append(String.format(" ------------ ID: %s%s", item.getId(), sepor))
                .toString();

        new StartUI(input, tracker).init();
        assertThat(new String(out.toByteArray()), Is.is(this.toString(result)));
    }

    /**
     * В методе вынесли функцианал которой повторяется в каждом тесте.
     * @param info передаем в метод с меню функционал основного теста.
     * @return список в той последовательности что мы ожидаем.
     */
    private String toString(StringBuilder info) {
        String menu = String.format("Menu.%sSelect a menu item :%s0. Add new Item%s1. Show all items%s2. Edit item%s3. Delete item%s4. Find item by Id%s5. Find items by name%s6. Exit for program%s",
                sepor, sepor, sepor, sepor, sepor, sepor, sepor, sepor, sepor);
        StringBuilder stringBuilder = new StringBuilder();
        return stringBuilder.append(menu)
                            .append(info)
                            .append(menu).toString();
    }
}
