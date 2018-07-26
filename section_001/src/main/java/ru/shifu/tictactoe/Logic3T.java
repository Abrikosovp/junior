package ru.shifu.tictactoe;
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
     * Проверяет выигрышные комбинации для X.
     * @return true если победил X и false если не победил
     */
    public boolean isWinnerX() {
        boolean result = false;
        for (int i = 0; i < this.table.length - 1; i++) {
            if (this.table[i][i].hasMarkX() == this.table[i + 1][i + 1].hasMarkX()
                    && this.table[this.table.length - i - 1][i].hasMarkX() == this.table[this.table.length - i - 2][i + 1].hasMarkX()) {
                result = true;
            } else {
                for (int j = 0; j < this.table.length - 1; j++) {
                    if (this.table[j][i].hasMarkX() == this.table[j + 1][i].hasMarkX() &&
                            this.table[i][j].hasMarkX() == this.table[i][j + 1].hasMarkX()) {
                        result = true;
                    }
                }
            }
        }
        return result;
    }
    /**
     * Проверяет выигрышные комбинации для О.
     * @return true если победил О и false если не победил
     */
    public boolean isWinnerO() {
        boolean result = false;
        for (int i = 0; i < this.table.length -1; i++) {
            if (this.table[i][i].hasMarkO() == this.table[i + 1][i + 1].hasMarkO()
                    && this.table[this.table.length - i - 1][i].hasMarkO() == this.table[this.table.length - i - 2][i + 1].hasMarkO()) {
                result = true;
            } else {
                for (int j = 0; j < this.table.length - 1; j++) {
                    if (this.table[i][j].hasMarkO() == this.table[i][j + 1].hasMarkO()
                            && this.table[j][i].hasMarkO() == this.table[j + 1][i].hasMarkO()) {
                        result = true;
                    }
                }
            }
        }
        return result;
    }

    /**
     * Проверяет есть ли пустые клетки.
     * @return true если есть пустые и false если нет пустых.
     */
    public boolean hasGap() {
        boolean result = false;
        for (int i = 0; i < this.table.length; i++) {
            for (int j = 0; j < this.table.length; j++) {
                if (!table[i][j].hasMarkO() && !table[i][j].hasMarkX()) {
                    result = true;
                    break;
                }
            }
        }
        return result;
    }
}
