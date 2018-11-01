package ru.shifu.list;
import org.hamcrest.core.Is;
import org.junit.Assert;
import org.junit.Test;
/**
 * SimpleQueueTest.
 *
 * @author Pavel Abrikosov (abrikosovp@mail.ru).
 * @version 1.
 * @since 1.11.2018.
 **/
public class SimpleQueueTest {

    @Test
    public void whenPushAndPollElements() {
        SimpleQueue<Integer> simpleQueue = new SimpleQueue<>();


        simpleQueue.push(1);
        simpleQueue.push(2);
        simpleQueue.push(3);

        Assert.assertThat(simpleQueue.poll(), Is.is(1));
        Assert.assertThat(simpleQueue.poll(), Is.is(2));
        Assert.assertThat(simpleQueue.poll(), Is.is(3));
    }

    @Test
    public void whenQueueIsEmptyMustReturnNull() {
        SimpleQueue<Integer> simpleQueue = new SimpleQueue<>();

        Assert.assertThat(simpleQueue.poll(), Is.is((Integer) null));
    }

    @Test
    public void whenPushAndPollAllElementsAndThenPushAndPoll() {
        SimpleQueue<Integer> simpleQueue = new SimpleQueue<>();

        simpleQueue.push(1);
        simpleQueue.push(2);
        simpleQueue.push(3);
        simpleQueue.poll();
        simpleQueue.poll();
        simpleQueue.poll();

        simpleQueue.push(4);
        simpleQueue.push(5);

        Assert.assertThat(simpleQueue.poll(), Is.is(4));
        Assert.assertThat(simpleQueue.poll(), Is.is(5));
        Assert.assertThat(simpleQueue.poll(), Is.is((Integer) null));
    }
}