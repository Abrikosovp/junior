package ru.shifu.list;

/**
 * SimpleQueue.
 *
 * @author Pavel Abrikosov (abrikosovp@mail.ru).
 * @version 1.
 * @since 1.11.2018.
 **/
public class SimpleQueue<E> {

    private SimpleStack<E> inputStack = new SimpleStack<>();
    private SimpleStack<E> outputStack = new SimpleStack<>();

    /**
     * Метод добавляет данные в конец стека в in.
     * Если добавляется первый элемент, то начало и конец совпадают.
     * Иначе добавляем в конец.
     * @param value данные
     */
    public void push(E value) {
        inputStack.push(value);
    }

    /**
     * poll
     * Метод проверяет что outputStack пуст ,
     * затем перекачивает данные из in в out и
     * удаляющий последний элемент из стека.
     * Если удалять нечего возвращает нуль, иначе данные удаленной ячейки.
     * @return данные.
     */
    public E poll() {
       if (outputStack.isEmpty()) {
           while (!inputStack.isEmpty()) {
               outputStack.push(inputStack.poll());
           }
       }
       return outputStack.isEmpty() ? null : outputStack.poll();
    }
}
