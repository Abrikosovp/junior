package ru.shifu.jmm;

import org.junit.Test;

/**
 * ProblemConcurrentCounter.
 *
 * @author Pavel Abrikosov (abrikosovp@mail.ru).
 * @version 1.
 * @since 19.11.2018.
 **/
public class ProblemConcurrentCounter {
    public static final int N = 1_000_000;
    public static int count = 0;

    @Test
    public void when() throws InterruptedException {
        /**
         * Поток 1 , который инкрементит счетчик count
         */
        Thread t0 = new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < N; i++) {
                    count++;
                }
            }
        });
        t0.start();

        /**
         * Поток 2 , который инкрементит счетчик count
         */
        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < N; i++) {
                    count++;
                }
            }
        });
        t1.start();

        /**
         * Ждем пока потоки закончат работу.
         */
        t0.join();
        t1.join();

        System.out.println(count);
    }
}
