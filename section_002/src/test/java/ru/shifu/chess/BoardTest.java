package ru.shifu.chess;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import ru.shifu.chess.exceptions.FigureNotFoundException;
import ru.shifu.chess.exceptions.ImpossibleMoveException;
import ru.shifu.chess.exceptions.OccupiedWayException;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

/**
 * BoardTest .
 *
 * @author Pavel Abrikosov (abrikosovp@mail.ru).
 * @version 1.
 * @since 14.10.2018.
 **/
public class BoardTest {
    /**
     * Правильный ход слона с 8,1 на 2,7. Без других фигур на доске.
     */
    @Test
    public void whenBishopRightMoveWithoutFigures() {
        Cell source = new Cell(8, 1);
        Cell dest = new Cell(1, 8);
        Bishop bishop = new Bishop(source);
        Board board = new Board();
        board.add(bishop);
        boolean expected = board.move(source, dest);
        assertThat(expected, is(true));
    }
    /**
     * Неправильный ход слона с 8,1 на 8,8. Без других фигур на доске.
     * Выбрасывается исключение ImpossibleMoveException.
     */
    @Rule
    public ExpectedException impossibl = ExpectedException.none();

    @Test
    public void whenBishopWrongMoveWithoutFigures() throws ImpossibleMoveException {
        impossibl.expect(ImpossibleMoveException.class);
        impossibl.expectMessage("Impossible move");
        Cell source = new Cell(8, 1);
        Cell dest = new Cell(8, 8);
        Bishop bishop = new Bishop(source);
        Board board = new Board();
        board.add(bishop);
        boolean result = board.move(source, dest);
    }
    /**
     * В исходной ячейке нет слона.
     * Выбрасывается исключение FigureNotFoundException.
     */
    @Rule
    public ExpectedException empty = ExpectedException.none();

    @Test
    public void whenBishopWrongMoveFigureNotFoundException1() throws FigureNotFoundException {
        empty.expect(FigureNotFoundException.class);
        empty.expectMessage("Figure not found");
        Cell source = new Cell(8, 1);
        Cell dest = new Cell(8, 8);
        Board board = new Board();
        boolean result = board.move(source, dest);
    }
    /**
     * Неправильно указана исходная ячейка - в ней нет слона. Без других фигур на доске.
     * Выбрасывается исключение FigureNotFoundException.
     */
    @Rule
    public ExpectedException emptyfully = ExpectedException.none();

    @Test
    public void whenBishopWrongMoveFigureNotFoundException2() throws FigureNotFoundException {
        emptyfully.expect(FigureNotFoundException.class);
        emptyfully.expectMessage("Figure not found");
        Cell source = new Cell(8, 1);
        Cell dest = new Cell(1, 8);
        Cell isEmpty = new Cell(6, 3);
        Bishop bishop = new Bishop(source);
        Board board = new Board();
        board.add(bishop);
        boolean expected = board.move(isEmpty, dest);
    }
    /**
     * Ход слона с 8,1 на 2,7 через занятую ячейку (с 6,3 другой фигурой ).
     * Выбрасывается исключение OccupiedWayException.
     */
    @Rule
    public ExpectedException notpass = ExpectedException.none();

    @Test
    public void whenBishopWrongMoveOccupiedWayException() throws OccupiedWayException {
        notpass.expect(OccupiedWayException.class);
        notpass.expectMessage("The figure can not pass");
        Cell source = new Cell(8, 1);
        Cell dest = new Cell(1, 8);
        Bishop bishop = new Bishop(source);
        Board board = new Board();
        board.add(bishop);
        Cell occupied = new Cell(6, 3);
        Bishop bishopOccupi = new Bishop(occupied);
        board.add(bishopOccupi);
        boolean result = board.move(source, dest);

    }
}
