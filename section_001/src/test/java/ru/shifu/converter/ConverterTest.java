package ru.shifu.converter;

import org.junit.Test;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;
/**
 * Converter.
 *
 * @author Pavel Abrikosov (abrikosovp@mail.ru).
 * @version 1.
 * @since 23.06.2018.
 */
public class ConverterTest {
    /**
     * Конвертируем рубли в евро.
     */
    @Test
    public void when60RubleToDollarThen1() {
        Converter converter = new Converter();
        int result = converter.rubleToDollar(60);
        assertThat(result, is(1));
    }
    /**
     * Конвертируем рубли в доллары.
     */
    @Test
    public void when70RubleToEuroThen1() {
        Converter converter = new Converter();
        int result = converter.rubleToEuro(70);
        assertThat(result, is(1));
    }
    /**
     * Конвертируем рубли в евро.
     */
    @Test
    public void when2EvroToRubleThen140() {
        Converter converter = new Converter();
        int result = converter.evroToRuble(2);
        assertThat(result, is(140));
    }
    /**
     * Конвертируем рубли в доллор.
     */
    @Test
    public void when2DollorToRubleThen120() {
        Converter converter = new Converter();
        int result = converter.dollorToRuble(2);
        assertThat(result, is(120));
    }
}