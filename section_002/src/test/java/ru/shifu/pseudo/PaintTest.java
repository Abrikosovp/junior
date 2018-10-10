package ru.shifu.pseudo;

import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
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
        PrintStream stdout = System.out;
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        System.setOut(new PrintStream(out));
        Paint paint = new Paint();
        paint.setShape(new Square());
        paint.executeStrategy();
        assertThat(new String(out.toByteArray()), is(new StringBuilder()
                .append("++++\n")
                .append("+  +\n")
                .append("+  +\n")
                .append("++++\n")
                .append(System.lineSeparator())
                .toString()
                )
        );
        System.setOut(stdout);
    }
    /**
     * Тест проверят треугольник.
     */
    @Test
    public void whenDrawTriangle() {
        PrintStream stream = System.out;
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        System.setOut(new PrintStream(out));
        Paint triangle = new Paint();
        triangle.setShape(new Triangle());
        triangle.executeStrategy();
        assertThat(new String(out.toByteArray()), is(new StringBuilder()
                .append("   +   \n")
                .append("  +++  \n")
                .append(" +++++ \n")
                .append("+++++++\n")
                .append(System.lineSeparator())
                .toString()));
        System.setOut(stream);
    }
}
