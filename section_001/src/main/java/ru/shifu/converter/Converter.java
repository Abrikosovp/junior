package ru.shifu.converter;
/**
 * Converter.
 *
 * @author Pavel Abrikosov (abrikosovp@mail.ru).
 * @version 1.
 * @since 23.06.2018.
 */
public class Converter {
    private static final int EVRO = 70;
    private static final int DOLLOR = 60;
    /**
     * Конвертируем рубли в евро.
     * @param value рубли.
     * @return Евро.
     */
    public int rubleToEuro(int value) {
        return value / EVRO;
    }
    /**
     * Конвертируем рубли в доллары.
     * @param value рубли.
     * @return Доллары
     */
    public int rubleToDollar(int value) {
        return value / DOLLOR;
    }
    /**
     * Конвертируем рубли в евро.
     * @param value доллор.
     * @return Рубль
     */
    public int evroToRuble(int value) {
        return value * EVRO;
    }
    /**
     * Конвертируем рубли в доллор.
     * @param value доллор.
     * @return Рубль
     */
    public int dollorToRuble(int value) {
        return value * DOLLOR;
    }
}
