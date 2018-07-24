package ru.shifu.max;
import org.junit.Test;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;
/**
 * MaxTest - максимум из двух чисел.
 *
 * @author Pavel Abrikosov (abrikosovp@mail.ru).
 * @version 1.
 * @since 24.06.2018.
 */
public class MaxTest {
    /**
     * Максимум из двух.
     */
    @Test
    public void whenFirstLessSecond() {
        Max maxim = new Max();
        int result = maxim.max(1, 2);
        assertThat(result, is(2));
    }
    /**
     * Максимум из трех.
     */
    @Test
    public void whenFirstLessSecondAndThird() {
        Max max = new Max();
        int result = max.max(5, 10, 15);
        assertThat(result, is(15));
    }
}
