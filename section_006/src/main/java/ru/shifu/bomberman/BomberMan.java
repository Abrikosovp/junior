package ru.shifu.bomberman;


public class BomberMan {
    private Cell position;

    public BomberMan(int x, int y) {
        this.position = new Cell(x, y);
    }

    public Cell getPosition() {
        return position;
    }

    public void setPosition(Cell position) {
        this.position = position;
    }

    @Override
    public String toString() {
        return String.format("Current Bomber location us %s", this.position);
    }
}
