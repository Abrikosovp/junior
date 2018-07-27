package ru.shifu.tictactoe;

import java.util.function.Predicate;

/**
 *  Класс Logic3T отвечает за проверку логики.
 *
 * @author Pavel Abrikosov (abrikosovp@mail.ru).
 * @version 1.
 * @since 26.07.2018.
 **/
public class Logic3T {
    private final Figure3T[][] table;

    public Logic3T(Figure3T[][] table) {
        this.table = table;
    }

    /**
     * Общий , универсальный метод, который вычисляет все последовательности.
     * @param predicate  это параметризованное поведение. Мы его вводим, так как у нас два события: проверка на X и О.
     * @param startX начальная точка.
     * @param startY начальная точка
     * @param deltaX движение.
     * @param deltaY движение.
     * @return true если есть победитель  или false если нет.
     */
    public boolean fillBy(Predicate<Figure3T> predicate, int startX, int startY, int deltaX, int deltaY) {
        boolean result = true;
        for (int index = 0; index != this.table.length; index++) {
            Figure3T cell = this.table[startX][startY];
            startX += deltaX;
            startY += deltaY;
            if (!predicate.test(cell)) {
                result = false;
                break;
            }
        }
        return result;
    }

    /**
     * Проверяет выигрышные комбинации для X.
     * @return true если победил X и false если не победил
     */
    public boolean isWinnerX() {
                           return this.fillBy(Figure3T::hasMarkX, 0, 0, 1, 0)
                               || this.fillBy(Figure3T::hasMarkX, 0, 0, 0, 1)
                               || this.fillBy(Figure3T::hasMarkX, 0, 0, 1, 1)
                               || this.fillBy(Figure3T::hasMarkX, this.table.length - 1, 0, -1, 1)
                               || this.fillBy(Figure3T::hasMarkX, 1, 0, 0, 1)
                               || this.fillBy(Figure3T::hasMarkX, 2, 0, 0, 1)
                               || this.fillBy(Figure3T::hasMarkX, 0, 1, 1, 0)
                               || this.fillBy(Figure3T::hasMarkX, 0, 2, 1, 0);

                    }
    /**
     * Проверяет выигрышные комбинации для О.
     * @return true если победил О и false если не победил
     */
    public boolean isWinnerO() {
               return this.fillBy(Figure3T::hasMarkO, 0, 0, 0, 1)
                   || this.fillBy(Figure3T::hasMarkO, 0, 0, 1, 0)
                   || this.fillBy(Figure3T::hasMarkO, 0, 0, 1, 1)
                   || this.fillBy(Figure3T::hasMarkO, 0, this.table.length - 1, 1, -1)
                   || this.fillBy(Figure3T::hasMarkO, 1, 0, 0, 1)
                   || this.fillBy(Figure3T::hasMarkO, 2, 0, 0, 1)
                   || this.fillBy(Figure3T::hasMarkO, 0, 1, 1, 0)
                   || this.fillBy(Figure3T::hasMarkO, 0, 2, 1, 0);
    }

    /**
     * Проверяет есть ли пустые клетки.
     * @return true если есть пустые и false если нет пустых.
     */
    public boolean hasGap() {
        boolean result = false;
        for (int i = 0; i < this.table.length; i++) {
            for (int j = 0; j < this.table.length; j++) {
                if (table[i][j] != null) {
                    result = true;
                    break;
                }
            }
        }
        return result;
    }
}
