package ru.shifu.pseudo;
import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;
/**
 * PaintTest .
 *
 * @author Pavel Abrikosov (abrikosovp@mail.ru).
 * @version 1.
 * @since 10.10.2018.
 **/
public class PaintTest {
    /**
     * Тест проверят квадрат.
     */
    @Test
    public void whenDrawSquare() {
        assertThat(new Square().draw(), is(new StringBuilder()
                .append("++++\n")
                .append("+  +\n")
                .append("+  +\n")
                .append("++++\n")
                .toString()));
    }
    /**
     * Тест проверят треугольник.
     */
    @Test
    public void whenDrawTriangle() {
        assertThat(new Triangle().draw(), is(new StringBuilder()
                .append("   +   \n")
                .append("  +++  \n")
                .append(" +++++ \n")
                .append("+++++++\n")
                .toString()));
    }
}
