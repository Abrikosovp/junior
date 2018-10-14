package ru.shifu.chess;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import ru.shifu.chess.exceptions.ImpossibleMoveException;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * BishopTest .
 *
 * @author Pavel Abrikosov (abrikosovp@mail.ru).
 * @version 1.
 * @since 14.10.2018.
 **/
public class BishopTest {
    @Rule
    public final ExpectedException expectedException = ExpectedException.none();

    /**
     * Проверяет метод way.
     */
    @Test
    public void whenMovingDiagonallyTneArraySteps() throws ImpossibleMoveException {
        Cell source = new Cell(8, 1);
        Cell dest = new Cell(1, 8);
        Bishop bishop = new Bishop(source);
        int expected = bishop.way(source, dest).length;
        int result = 7;
        assertThat(expected, is(result));
    }

    /**
     * Неправильный ход слона с 2,0 на 5,2. Без других фигур на доске.
     * Выбрасывается исключение ImpossibleMoveException.
     */
    @Test
    public void whenNotMovingTneException() throws ImpossibleMoveException {
        expectedException.expect(ImpossibleMoveException.class);
        expectedException.expectMessage("Impossible move");
        Cell source = new Cell(8, 1);
        Cell dest = new Cell(2, 8);
        Bishop bishop = new Bishop(source);
        Cell[] expected = bishop.way(source, dest);
    }
}