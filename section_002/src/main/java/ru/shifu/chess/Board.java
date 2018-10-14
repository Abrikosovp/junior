package ru.shifu.chess;

import ru.shifu.chess.exceptions.FigureNotFoundException;
import ru.shifu.chess.exceptions.ImpossibleMoveException;
import ru.shifu.chess.exceptions.OccupiedWayException;

/**
 * BoardTest шахматная доска.
 *
 * @author Pavel Abrikosov (abrikosovp@mail.ru).
 * @version 1.
 * @since 14.10.2018.
 **/
public class Board {

    /**
     * Максимальное количество фигур на доске.
     */
    private static final int FINISH = 32;

    /**
     * Фигуры, находящиеся на доске.
     */
    Figure[] figures = new Figure[FINISH];

    /**
     * Вспомогательное поле для учета текущего количества фигур.
     */
    private int pos = 0;

    /**
     * Метод добавляет фигуру на доску.
     * @param figure
     */
    public void add(Figure figure) {
        this.figures[pos++] = figure;
    }
    /**
     * Метод осуществляет ход фигуры по доске.
     * Метод должен проверить:
     * - Что в заданной ячейки есть фигура. если нет. то выкинуть исключение.
     * - Если фигура есть. Проверить может ли она так двигаться. Если нет то упадет исключение.
     * - Проверить что полученный путь. не занят фигурами. Если занят выкинуть исключение.
     * - Если все отлично. Записать в ячейку новое новое положение Figure figure.copy(Cell dest).
     * @param source source - исходная ячейка, в которой стоит фигура.
     * @param dest - ячейка, куда следует пойти.
     * @return
     * @throws ImpossibleMoveException
     * @throws OccupiedWayException
     * @throws FigureNotFoundException
     */
    boolean move(Cell source, Cell dest) throws ImpossibleMoveException, OccupiedWayException, FigureNotFoundException {
        //проверяем что фигура на позиции
        int index = findFigure(source);
        if (index < 0) {
            throw new FigureNotFoundException("Figure not found");
        }

        //Вызываем метод который проверяет может лифигура так ходить
        //и выдает массив с координатами всего пути фигуры
        Cell[] steps = this.figures[index].way(source, dest);

        //цикл который проверяет путь фигуры на наличие других фигур
        for (Cell cell : steps) {
            int everyStep = this.findFigure(cell);
            if (everyStep  > 0) {
                throw new OccupiedWayException("The figure can not pass");
            }
        }
        this.figures[index].copy(dest);
        return true;
    }
    /**
     * Метод совершает 2 проверки:
     * 1. Фигуры нет на месте return -1 , если фигура на месте return 1 так как условия все ТРУ
     * 2. Проверяет путь фигуры: может ли фигура пройти: если да return -1 . если нет return 1
     *
     * @param source - исходная ячейка, в которой стоит фигура.
     * @return номер индекса найденной фигуры в массиве фигур.
     */
    private int findFigure(Cell source) {
        int index = - 1;
        //в цикле проверяем: что ячейка где создали новую фигуру не пустая ,
        // и проверяем что координаты что в ячейки совподают с координатами начального хода
        for (int i = 0; i < pos; i++) {
            if (this.figures[i] != null && this.figures[i].getPosition().x == source.x && this.figures[i].getPosition().y == source.y) {
                index = i;
            }
        }
        return index;
    }
}
