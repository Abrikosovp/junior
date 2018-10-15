package ru.shifu.search;

import java.util.LinkedList;

/**
 * PriorityQueue хранилище.
 *
 * @author Pavel Abrikosov (abrikosovp@mail.ru).
 * @version 1.
 * @since 15.10.2018.
 **/
public class PriorityQueue {
    /**
     * Хранилище.
     */
    private static LinkedList<Task> tasks = new LinkedList<>();
    /**
     * Метод: очередь с приоритетом.
     * Работает по принципу: есть счетчик который считает и самый большой элем ставит в конец
     *                       ели приходит маленький он его кидает на место большого
     * Тем самым он сортирует по приоритету от большего к меньшему
     * @param task task.
     */
    public void put(Task task) {
        int pos = 0;
        Task elem = null;
        for (Task value : this.tasks) {
            if (task.getPriority() >= value.getPriority()) {
                pos++;
            } else {
                elem = value;
                break;
            }
        }
        if (pos == tasks.size()) {
            this.tasks.add(pos, task);
        } else {
            tasks.add(tasks.indexOf(elem), task);
        }
    }
    /**
     * метод возвашает первый элемент с последующим удалением.
     * @return элемент.
     */
    public Task task() {
       return this.tasks.poll();
    }
}
