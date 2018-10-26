package ru.shifu;
import org.junit.Test;
import ru.shifu.Calculate;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;
/**
 * CalculateTest.
 *
 * @author Pavel Abrikosov (abrikosovp@mail.ru).
 * @version 1.
 * @since 23.06.2018.
 */
public class CalculateTest {
    /**
     * Метод echo.
     */
    @Test
    public void thenEcho() {
        Calculate calculate = new Calculate();
        String result = calculate.echo("Павел");
        assertThat(result, is("Echo, echo, echo: Павел"));
    }
}
