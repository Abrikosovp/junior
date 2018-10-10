package ru.shifu.pseudo;

import org.junit.After;
import org.junit.Before;
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
     * Тест проверят квадрат.
     */
    @Test
    public void whenDrawSquare() {
        String string = new StringBuilder()
                .append("++++\n")
                .append("+  +\n")
                .append("+  +\n")
                .append("++++\n")
                .append(System.lineSeparator())
                .toString();
        Paint paint = new Paint();
        paint.setShape(new Square());
        paint.executeStrategy();
        assertThat(new String(out.toByteArray()), is(string));
    }
    /**
     * Тест проверят треугольник.
     */
    @Test
    public void whenDrawTriangle() {
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
    }
}
