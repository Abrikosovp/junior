package ru.shifu.pool;

import org.junit.Test;
/**
 * ThreadPoolTest.
 *
 * @author Pavel Abrikosov (abrikosovp@mail.ru).
 * @version 1.
 * @since 24.11.2018.
 **/
public class ThreadPoolTest {

    public class Job implements Runnable {
        int number = 0;

        public Job(int num) {
            this.number = num;
        }

        @Override
        public void run() {
            System.out.println(String.format("Thread %s doing JOB #%s", Thread.currentThread().getName(), number));
        }
    }

    @Test
    public void whenAddTenJobsThenWork() {
        ThreadPool pool = new ThreadPool();

        for (int index = 0; index < 10; index++) {
            pool.work(new Job(index));
        }
        pool.shutdown();
    }
}