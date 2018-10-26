package ru.shifu.array;
/**
 * TurnTest.
 *
 * @author Pavel Abrikosov (abrikosovp@mail.ru).
 * @version 1.
 * @since 25.06.2018.
 **/
import org.junit.Test;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

public class TurnTest {
    /**
     * Метод переварачивает массив задом наперёд
     * {4, 1, 6, 2};
     */
    @Test
    public void whenTurnArrayWithEvenAmountOfElementsThenTurnedArray() {
        Turn turner = new Turn();
        int[] input = new int[] {4, 1, 6, 2};
        int[] result = turner.turn(input);
        int[] expect = new int[] {2, 6, 1, 4};
        assertThat(result, is(expect));
    }
    /**
     * Метод переварачивает массив задом наперёд
     * 1, 2, 3, 4, 5}.
     */
    @Test
    public void whenTurnArrayWithOddAmountOfElementsThenTurnedArray() {
        Turn turns = new Turn();
        int[] input = {1, 2, 3, 4, 5};
        int[] result = turns.turn(input);
        int[] expected = {5, 4, 3, 2, 1};
        assertThat(result, is(expected));
    }
}