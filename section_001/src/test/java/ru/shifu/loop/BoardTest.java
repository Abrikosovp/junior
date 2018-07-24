package ru.shifu.loop;
import org.junit.Test;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;
/**
 * BoardTest - проверяет построили шахматную доску в псевдографике.
 *
 * @author Pavel Abrikosov (abrikosovp@mail.ru).
 * @version 1.
 * @since 24.06.2018.
 */
public class BoardTest {
    /**
     * Для доски 3 на 3 вид:
     *   Х Х
     *    Х
     *   Х Х
     */
    @Test
    public void whenPaintBoardWithWidthThreeAndHeightThreeThenStringWithThreeColsAndThreeRows() {
        Board board = new Board();
        String result = board.paint(3, 3);
        final String line = System.getProperty("line.separator");
        String expected = String.format("X X%s X %sX X%s", line, line, line);
        assertThat(result, is(expected));
    }
    /**
     *  Для доски 5 на 4 вид:
     *        Х	 Х  Х
     *        	Х Х
     *        Х	 Х 	Х
     *  	    Х Х
     */
    @Test
    public void whenPaintBoardWithWidthFiveAndHeightFourThenStringWithFiveColsAndFourRows() {
        //напишите здесь тест, проверяющий формирование доски 5 на 4.
        Board board = new Board();
        String result = board.paint(5, 4);
        final String line = System.lineSeparator();
        String expected = String.format("X X X%s X X %sX X X%s X X %s", line, line, line, line);
    }
}
