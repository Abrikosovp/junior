package ru.shifu.pseudo;
/**
 * Square .
 *
 * @author Pavel Abrikosov (abrikosovp@mail.ru).
 * @version 1.
 * @since 10.10.2018.
 **/
public class Square implements Shape {
    /**
     * Метод изображающий псевдр квадрат.
     * @return строку.
     */
    @Override
    public String draw() {
        StringBuilder str = new StringBuilder();
        str.append("++++\n");
        str.append("+  +\n");
        str.append("+  +\n");
        str.append("++++\n");
        return str.toString();
    }
}
