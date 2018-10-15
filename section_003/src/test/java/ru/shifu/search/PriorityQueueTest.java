package ru.shifu.search;

import org.junit.Test;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;
/**
 * PriorityQueueTest хранилище.
 *
 * @author Pavel Abrikosov (abrikosovp@mail.ru).
 * @version 1.
 * @since 15.10.2018.
 **/
public class PriorityQueueTest {
    /**
     * Очередь из 3 элементов
     */
    @Test
    public void whenHigherPriority1() {
        PriorityQueue queue = new PriorityQueue();
        queue.put(new Task("I", 3));
        queue.put(new Task("mama", 1));
        queue.put(new Task("papa", 2));
        assertThat(queue.task().getDesc(),is("mama"));
    }

    /**
     * Очередь из 5 элементов
     */
    @Test
    public void whenHigherPriority2() {
        PriorityQueue queue = new PriorityQueue();
        queue.put(new Task("mama", 5));
        queue.put(new Task("papa", 2));
        queue.put(new Task("Irina", 3));
        queue.put(new Task("Antuan", 4));
        queue.put(new Task("I", 1));
        assertThat(queue.task().getDesc(),is("I"));
    }
}

