package ru.shifu.bomberman;

public class Cell {
    /**
     * Внутренние поля класса
     */
    private int posX;
    private int posY;

    /**
     * Конструктор класса
     * @param posX
     * @param posY
     */
    public Cell(int posX, int posY) {
        this.posX = posX;
        this.posY = posY;
    }

    public int getPosX() {
        return posX;
    }

    public int getPosY() {
        return posY;
    }

    public void setPosX(int posX) {
        this.posX = posX;
    }

    public void setPosY(int posY) {
        this.posY = posY;
    }

    @Override
    public String toString() {
        return String.format("(Cell x = %s, y = %s", this.posX, this.posY);
    }
}
