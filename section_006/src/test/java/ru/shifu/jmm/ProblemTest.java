package ru.shifu.jmm;

import org.junit.Test;

/**
 * ProblemTest.
 *
 * @author Pavel Abrikosov (abrikosovp@mail.ru).
 * @version 1.
 * @since 29.10.2018.
 **/
public class ProblemTest {
    /**
     * счетчик
     */
    private volatile int counter = 0;

    /**
     * Основной поток, который приводит в действие
     * два тестовых потока.
     */
    @Test
    public void when() {
        ProblemTest problemTest = new ProblemTest();
        new Thread(problemTest.new Task()).start();
        new Thread(problemTest.new Task()).start();
    }

    /**
     * Метод в котором происходит инкримент переменной
     * и выводит до и после инкрементации.
     */
    private synchronized void performTask() {
        int tmp = counter;
        counter++;
        System.out.println(String.format("%s - before: %s, after: %s", Thread.currentThread().getName(), tmp, counter));
    }

    /**
     * Класс содержет метод
     * который содержит метод который нужно выполнять потоком
     */
    class Task implements Runnable {
        @Override
        public void run() {
            for (int i = 0; i < 5; i++) {
                performTask();
            }
        }
    }
}
