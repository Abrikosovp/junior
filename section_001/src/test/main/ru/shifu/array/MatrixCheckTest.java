package ru.shifu.array;
/**
 * MatrixCheckTest - Квадратный массив заполнен true или false по диагоналям
 *
 * @author Pavel Abrikosov (abrikosovp@mail.ru).
 * @version 1.
 * @since 26.06.2018.
 **/
import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class MatrixCheckTest {
    /**
     * Метод проверяет, что все элементы по диагоналям равны true или false.
     *   матрица 3х3 - ответ true.
     */
    @Test
    public void whenDataMonoByTrueThenTrueSize3x3() {
        MatrixCheck check = new MatrixCheck();
        boolean[][] input = new boolean[][] {
                {true, true, true},
                {false, true, true},
                {true, false, true}
        };
        boolean result = check.mono(input);
        assertThat(result, is(true));
    }
    /**
     * Метод проверяет, что все элементы по диагоналям равны true или false.
     *   матрица 3х3 - ответ false.
     */
    @Test
    public void whenDataNotMonoByTrueThenFalseSize3x3() {
        MatrixCheck check = new MatrixCheck();
        boolean[][] input = new boolean[][] {
                {true, true, false},
                {false, false, true},
                {true, false, true}
        };
        boolean result = check.mono(input);
        assertThat(result, is(false));
    }
    /**
     * Метод проверяет, что все элементы по диагоналям равны true или false.
     *   матрица 4х4 - ответ true.
     */
    @Test
    public void whenDataMonoByTrueThenTrueSize4x4() {
        MatrixCheck check = new MatrixCheck();
        boolean[][] input = new boolean[][] {
                {true, true, false, true},
                {false, true, true, false},
                {true, true, true, true},
                {true, true, false, true}
        };
        boolean result = check.mono(input);
        assertThat(result, is(true));
    }
    /**
     * Метод проверяет, что все элементы по диагоналям равны true или false.
     *   матрица 4х4 - ответ false.
     */
    @Test
    public void whenDataNotMonoByTrueThenFalseSize4x4() {
        MatrixCheck check = new MatrixCheck();
        boolean[][] input = new boolean[][] {
                {false, true, false, true},
                {false, true, true, false},
                {true, true, true, true},
                {true, true, false, false}
        };
        boolean result = check.mono(input);
        assertThat(result, is(false));
    }
}