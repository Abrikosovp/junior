package ru.shifu.chess;

/**
 * Ячейка шахматной доски.
 * Характеризуется координатами x и y.
 */
public class Cell {
    /**
     * Поля должны быть private для потдержания инкопсуляции
     * но я выбрал public для учебнях целей.
     */
    public int x;
    public int y;

    /**
     * Конструктор ячейки.
     * @param x - координата ячейки по оси x, x изменяется от 1 до конца доски, обычно до 8.
     * @param y - координата ячейки по оси y, y изменяется от 1 до конца доски, обычно до 8.
     */
    public Cell(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }
}
