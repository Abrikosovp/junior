package ru.shifu.bomberman;
/**
 * Monster.
 *
 * @author Pavel Abrikosov (abrikosovp@mail.ru).
 * @version 1.
 * @since 27.11.2018.
 **/
public class Monster {
    private Cell position;
    private int number;

    public Monster(Cell position, int number) {
        this.position = position;
        this.number = number;
    }

    public Cell getPosition() {
        return position;
    }

    public int getNumber() {
        return number;
    }

    public void setPosition(Cell position) {
        this.position = position;
    }
}
