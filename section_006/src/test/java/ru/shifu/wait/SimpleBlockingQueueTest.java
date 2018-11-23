package ru.shifu.wait;

import org.junit.Test;
/**
 * SimpleBlockingQueueTest.
 *
 * @author Pavel Abrikosov (abrikosovp@mail.ru).
 * @version 1.
 * @since 24.11.2018.
 **/
public class SimpleBlockingQueueTest {

    @Test
    public void when() {
        SimpleBlockingQueue<Integer> queue = new SimpleBlockingQueue<>();

        Thread producer = new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                queue.offer(i);
                System.out.println(String.format("%s положил %s", Thread.currentThread().getName(), i));
            }
        });

        Thread consumer = new Thread(() -> {
            try {
                Thread.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            for (int i = 0; i < 10; i++) {
                System.out.println(String.format("%s достал %s", Thread.currentThread().getName(), queue.poll()));
            }
        });

        consumer.start();
        producer.start();
        try {
            producer.join();
            consumer.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}