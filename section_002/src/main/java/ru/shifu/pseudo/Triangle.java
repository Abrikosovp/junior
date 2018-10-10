package ru.shifu.pseudo;
/**
 * Triangle .
 *
 * @author Pavel Abrikosov (abrikosovp@mail.ru).
 * @version 1.
 * @since 10.10.2018.
 **/
public class Triangle implements Shape {
    /**
     * Метод изображающий псевдр треугольник.
     * @return строку.
     */
    @Override
    public String draw() {
        StringBuilder str = new StringBuilder();
        str.append("   +   \n");
        str.append("  +++  \n");
        str.append(" +++++ \n");
        str.append("+++++++\n");
        return str.toString();
    }
}
