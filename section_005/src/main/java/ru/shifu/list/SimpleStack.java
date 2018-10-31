package ru.shifu.list;
/**
 * SimpleStack.
 *
 * @author Pavel Abrikosov (abrikosovp@mail.ru).
 * @version 1.
 * @since 31.10.2018.
 **/
public class SimpleStack<E> {

    private SimpleLinkedList<E> simpleDequeue = new SimpleLinkedList<>();

    /**
     * push.
     * Метод, добавляет данные в конец стека.
     * Если добавляется первый элемент, то начало и конец совпадают.
     * Иначе добавляем в конец.
     * @param value данные, помещаемые в новую ячейку.
     * */
    public void push(E value) {
        simpleDequeue.insertFirst(value);
    }

    /**
     * poll.
     * Метод, удаляющий последний элемент из стека.
     * Если удалять нечего возвращает нуль, иначе данные удаленной ячейки.
     * @return E данные.
     * */
    public E poll() {
        return simpleDequeue.delete();
    }

    public boolean isEmpty() {
        return simpleDequeue.size() == 0;
    }
}
