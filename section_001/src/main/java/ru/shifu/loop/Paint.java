package ru.shifu.loop;
import java.util.function.BiPredicate;
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
        return this.loopBy(
                height,
                height,
                (row, colum) -> row >= colum
        );
    }

    /**
     * Стороим левую часть пирамиды.
     * @param height size.
     * @return результат.
     */
    public String leftTrl(int height) {
        return this.loopBy(
                height,
                height,
                (row, colum) -> row >= height - colum - 1
        );
    }

    /**
     * Строим посностью пирамиду.
     * @param height size.
     * @return результат.
     */
    public String pyramid(int height) {
      return this.loopBy(
              height,
              2 * height - 1,
              (row, colum) -> row >= height - colum - 1 && row + height - 1 >= colum
      );
    }

    private String loopBy(int height, int weight, BiPredicate<Integer, Integer> predict) {
        StringBuilder screen = new StringBuilder();
        String ls = System.lineSeparator();
        for (int row = 0; row != height; row++) {
            for (int column = 0; column != weight; column++) {
                if (predict.test(row, column)) {
                    screen.append("^");
                } else {
                    screen.append(" ");
                }
            }
            screen.append(ls);
        }
        return screen.toString();
    }
}
