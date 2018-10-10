package ru.shifu.pseudo;
/**
 * Paint класс реализующий паттерн Strategy.
 *
 * @author Pavel Abrikosov (abrikosovp@mail.ru).
 * @version 1.
 * @since 10.10.2018.
 **/
public class Paint {
    Shape shape;

    public void setShape(Shape shape) {
        this.shape = shape;
    }

    public void executeStrategy() {
        System.out.println(this.shape.draw());
    }

    public static void main(String[] args) {
        Paint paint = new Paint();

        paint.setShape(new Triangle());
        paint.executeStrategy();

        paint.setShape(new Square());
        paint.executeStrategy();

    }
}
