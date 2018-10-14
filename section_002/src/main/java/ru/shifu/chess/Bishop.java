package ru.shifu.chess;

import ru.shifu.chess.exceptions.ImpossibleMoveException;

/**
 * Bishop класс, описывающий фигуру "Слон"..
 *
 * @author Pavel Abrikosov (abrikosovp@mail.ru).
 * @version 1.
 * @since 14.10.2018.
 **/
public class Bishop extends Figure {

    /**
     * Конструктор фигуры "Слон".
     * @param position - позиция фигуры (тип Cell).
     */
    public Bishop(Cell position) {
        super(position);
    }

    /**
     * Сходить фигурой.
     * Метод должен работать так.
     * dest - задает ячейку, куда следует пойти. Если фигура может туда пойти. то Вернуть массив ячеек. которые должна пройти фигура.
     * Если фигура туда пойти не может. выбросить исключение ImposibleMoveException
     *
     * @param source - исходная ячейка, в которой стоит фигура.
     * @param dest - ячейка, куда следует пойти.
     * @return - возвращает массив ячеек, которые должна пройти фигура. Ячейка source в этот массив не включается,
     * так как фигура уже стоит в этой ячейке. Ячейка dest - включается.
     * @throws ImpossibleMoveException - исключение, выбрасываемое, если фигура не может пойти в ячейку dest.
     *
     * Примечание: Слон ходит по-диагонали, поэтому следующая ячейка, куда делает ход слон, отличается от предыдущей
     * на dx = +1 или dx = -1, и dy = +1 или dy = -1.
     * переменные x,y = текущие координаты, через которые проходит слон.
     * Если слон вышел за границы доски - значит надо выбросить исключение ImpossibleMoveException.
     * Диапазон допустимых значений x и y = от 1 до SIZE. (для обычной доски SIZE = 8).
     */
    @Override
    Cell[] way(Cell source, Cell dest) throws ImpossibleMoveException {
        //проверяет может ли сделать такой ход
        if (!validWay(source, dest)) {
            throw new ImpossibleMoveException("Impossible move");
        }
        //высчитывает размар хода по формуле
        int sizeSteps = Math.abs(source.getX() - dest.getX());

        //выставляет в каую сторону будет двигаться фигура в системе координат
        Cell[] steps = new Cell[sizeSteps];
        int deltaX = source.getX() < dest.getX() ? 1 : -1;
        int deltaY = source.getY() < dest.getY() ? 1 : -1;

        //в цикле мы заполняем массив координатами движения фигуры
        for (int i = 1; i <= sizeSteps; i++) {
            steps[i - 1] = new Cell(source.getX() + i * deltaX, source.getY() + i * deltaY);
        }
       return steps;
    }

    /**
     * Метод проверяет что что если source.getX() - source.getX(), должно быть равно если source.getY() - dest.getY()
     * @param source - исходная ячейка, в которой стоит фигура.
     * @param dest - ячейка, куда следует пойти.
     * @return ели == true , если != false
     */
    private boolean validWay(Cell source, Cell dest) {
        return Math.abs(source.getX() - dest.getX()) == Math.abs(source.getY() - dest.getY());
    }

    /**
     * Метод создает новую фигуру с указанной координатой.
     * @param dest - координата фигуры.
     * @return new Bishop()
     */
    @Override
    Figure copy(Cell dest) {
        return new Bishop(dest);
    }

}
