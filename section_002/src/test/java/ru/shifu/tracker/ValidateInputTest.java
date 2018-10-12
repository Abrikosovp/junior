package ru.shifu.tracker;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
/**
 * ValidateInputTest .
 *
 * @author Pavel Abrikosov (abrikosovp@mail.ru).
 * @version 1.
 * @since 10.10.2018.
 **/
public class ValidateInputTest {
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
     * Тест проверяет, как программа реагирует если пользователь введет некорректные данные.
     */
    @Test
    public void whenInvalidInput() {
        ValidateInput input = new ValidateInput(new StubInput(new String[] {"invalid", "1"})); //создаём StubInput с последовательностью действий
        input.ask("enter", new int[] {1});
        assertThat(this.out.toString(), is(String.format("Please enter validate data again. %n")));

    }

}
