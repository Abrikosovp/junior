package ru.shifu.jmm;
/**
 * Problem.
 *
 * @author Pavel Abrikosov (abrikosovp@mail.ru).
 * @version 1.
 * @since 29.10.2018.
 **/
public class Problem {
    /**
     * счетчик
     */
    private volatile int counter = 0;

    /**
     * Основной поток, который приводит в действие
     * два тестовых потока.
     * @param args
     */
    public static void main(String[] args) {
        Problem problem = new Problem();
        new Thread(problem.new Task()).start();
        new Thread(problem.new Task()).start();
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
