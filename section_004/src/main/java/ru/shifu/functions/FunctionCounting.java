package ru.shifu.functions;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;
/**
 * FunctionCounting.
 *
 * @author Pavel Abrikosov (abrikosovp@mail.ru).
 * @version 1.
 * @since 25.10.2018.
 **/
public class FunctionCounting {
    /**
     * Метод подсчета функции в диапазоне.
     * @param start старт диапазона.
     * @param end финиш диапазона.
     * @param func функция.
     * @return result.
     */
    List<Double> diapason(int start, int end, Function<Double, Double> func) {
        List<Double> result = new ArrayList<>();
        for (Integer index = start; index <= end; index++) {
            result.add(func.apply(index.doubleValue()));
        }
        return result;
    }
}
