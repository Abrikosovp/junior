package ru.shifu.loop;

/**
 * Paint Построить пирамиду в псевдографике.
 *
 * @author Pavel Abrikosov (abrikosovp@mail.ru).
 * @version 1.
 * @since 24.06.2018.
 */

public class Paint {

    /**
     * Метод стоит правую часть пирамиды.
     * @param height size&
     * @return результат.
     */
    public String rightTrl(int height) {
        StringBuilder screen = new StringBuilder();
        String sl = System.lineSeparator();
        int weiter = height;
        for (int row = 0; row != height; row++) {
            for (int column = 0; column != weiter; column++) {
                if (row >= column) {
                    screen.append("^");
                } else {
                    screen.append(" ");
                }
            }
            screen.append(sl);
        }
        return screen.toString();
    }

    /**
     * Стороим левую часть пирамиды.
     * @param height size.
     * @return результат.
     */
    public String leftTrl(int height) {
        StringBuilder screen = new StringBuilder();
        String sl = System.lineSeparator();
        int weiter = height;
        for (int row = 0; row != height; row++) {
            for (int column = 0; column != weiter; column++) {
                if (row >= weiter - column - 1) {
                    screen.append("^");
                } else {
                    screen.append(" ");
                }
                }
                screen.append(sl);
            }
            return screen.toString();
    }

    /**
     * Строим посностью пирамиду.
     * @param height size.
     * @return результат.
     */
    public String pyramid(int height) {
        StringBuilder screen = new StringBuilder();
        int weight = 2 * height - 1;
        for (int row = 0; row != height; row++) {
            for (int column = 0; column != weight; column++) {
                if (row >= height - column - 1 && row + height - 1 >= column) {
                    screen.append("^");
                } else {
                    screen.append(" ");
                }
            }
            screen.append(System.lineSeparator());
        }
        return screen.toString();
    }
}
