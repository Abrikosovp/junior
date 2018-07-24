package ru.shifu.loop;
/**
 * Board - построить шахматную доску в псевдографике.
 *
 * @author Pavel Abrikosov (abrikosovp@mail.ru).
 * @version 1.
 * @since 24.06.2018.
 */
public class Board {
    /**
     * Метод стоит шахматную доску.
     * @param width горизонталь.
     * @param height вертикаль.
     * @return прорисованную шахматную доску.
     */
    public String paint(int width, int height) {
        StringBuilder screen = new StringBuilder();
        String sl = System.lineSeparator();
        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                if ((i + j) % 2 == 0) {
                    screen.append("X");
                } else {
                    screen.append(" ");
                }
            }
            screen.append(sl);
        }

        return screen.toString();
    }
}
