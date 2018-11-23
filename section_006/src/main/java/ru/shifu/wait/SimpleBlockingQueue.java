package ru.shifu.wait;

import net.jcip.annotations.GuardedBy;
import net.jcip.annotations.ThreadSafe;

import java.util.LinkedList;
import java.util.Queue;
/**
 * SimpleBlockingQueue.
 *
 * @author Pavel Abrikosov (abrikosovp@mail.ru).
 * @version 1.
 * @since 24.11.2018.
 **/
@ThreadSafe
public class SimpleBlockingQueue<T> {

    @GuardedBy("this")
    private Queue<T> queue;
    private int malLine = 10;

    public SimpleBlockingQueue() {
        this.queue =  new LinkedList<>();
    }

    public void offer(T value) {
        synchronized (this) {
            while (this.queue.size() > this.malLine) {
                try {
                    this.wait();
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            }

            this.queue.add(value);
            this.notifyAll();
        }
    }

    public T poll() {
        synchronized (this) {
            while (this.queue.isEmpty()) {
                try {
                    this.wait();
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            }

            this.notifyAll();
            return this.queue.poll();
        }
    }

    public boolean isEmpty() {
        return this.queue.isEmpty();
    }
}
