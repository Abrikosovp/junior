package ru.shifu.chess;

import ru.shifu.chess.exceptions.ImpossibleMoveException;
/**
 * Figure - абстрактный класс "Фигура"
 *
 * @author Pavel Abrikosov (abrikosovp@mail.ru).
 * @version 1.
 * @since 14.10.2018.
 **/
public abstract class Figure {
    /**
     * Координата фигуры.
     */
    private final Cell position;

    /**
     * Конструктор.
     * @param position - ячейка, куда помещается фигура на шахматной доске.
     */
    public Figure(Cell position) {
        this.position = position;
    }

    public Cell getPosition() {
        return position;
    }

    /**
     * Сходить фигурой.
     * Метод должен работать так.
     * dest - задает ячейку, куда следует пойти. Если фигура может туда пойти. то Вернуть массив ячеек. которые должна пройти фигура.
     * Если фигура туда пойти не может. выбросить исключение ImposibleMoveException
     * @param source - исходная ячейка.
     * @param dest - задает ячейку, куда следует пойти.
     * @return - возвращает массив ячеек, которые должна пройти фигура. Ячейка source в этот массив не включается,
     * так как фигура уже стоит в этой ячейке. Ячейка dest - включается.
     * @throws ImpossibleMoveException
     */
    abstract Cell[] way(Cell source, Cell dest) throws ImpossibleMoveException;

    /**
     * Пересоздать фигуру с новой координатой.
     * @param dest - координата фигуры.
     * @return - фигура.
     */
    abstract Figure copy(Cell dest);
}
