package ru.shifu.pool;

import ru.shifu.wait.SimpleBlockingQueue;

import java.util.LinkedList;
import java.util.List;
/**
 * ThreadPool.
 *
 * @author Pavel Abrikosov (abrikosovp@mail.ru).
 * @version 1.
 * @since 24.11.2018.
 **/
public class ThreadPool {
    private final List<PoolWorker> threads = new LinkedList<>();
    private final SimpleBlockingQueue<Runnable> tasks = new SimpleBlockingQueue<>();

    public ThreadPool() {
        for (int index = 0; index < Runtime.getRuntime().availableProcessors(); index++) {
            threads.add(new PoolWorker(tasks, index));
        }
    }

    public void work(Runnable job) {
        try {
            tasks.offer(job);
        } catch (Exception e) {
            System.out.println("Unable to add task to queue.");
        }
    }

    public void shutdown() {
        System.out.println("Shutdown");
        for (PoolWorker worker : threads) {
            worker.interrupt();
            System.out.println(String.format("%s is stops.", worker.getName()));
        }
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private class PoolWorker extends Thread {
        private final SimpleBlockingQueue<Runnable> t;

        public PoolWorker(SimpleBlockingQueue<Runnable> tasks, int index) {
            this.t = tasks;
            this.start();
            System.out.println(String.format("Thread %s is started", index));
        }

        @Override
        public void run() {
            Runnable task;
            while (!Thread.currentThread().isInterrupted()) {
                try {
                    task = t.poll();
                    task.run();
                } catch (Exception e) {
                    System.out.println(String.format("%s is interrupted.", Thread.currentThread().getName()));
                }
            }
        }
    }
}
