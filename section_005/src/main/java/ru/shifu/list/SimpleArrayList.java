package ru.shifu.list;
/**
 * SimpleArrayList.
 *
 * @author Pavel Abrikosov (abrikosovp@mail.ru).
 * @version 1.
 * @since 29.10.2018.
 **/
public class SimpleArrayList<E> {
    /**
     * Размер списка.
     */
    private int size;
    /**
     * Укозатель на первый элемент в списске.
     */
    private Node<E> first;

    /**
     * Метод вставляет в начало списка данные.
     * @param date Element.
     */
    public void add(E date) {
        Node<E> newLink = new Node<>(date);
        newLink.next = this.first;
        this.first = newLink;
        this.size++;
    }

    /**
     * Реализовать метод удаления первого элемент в списке.
     * @return Element.
     */
    public E delete() {
        Node<E> deleteNode = this.first;
        this.first = this.first.next;
        size--;
        return deleteNode.currentElement;
    }

    /**
     * Метод получения элемента по индексу.
     * @param index index.
     * @return Element.
     */
    public E get(int index) {
        Node<E> result = this.first;
        for (int i = 0; i < index; i++) {
            result = result.next;
        }
        return result.currentElement;
    }
    /**
     * Метод получения размера коллекции.
     */
    public int getSize() {
        return this.size;
    }


    /**
     * Класс предназначенный для храненя данных.
     * @param <E>
     */
    private static class Node<E> {
        /**
         * Сам элемент.
         */
        E currentElement;
        /**
         * Указатель на следующий элемент списка.
         */
        Node<E> next;

        Node(E date) {
            this.currentElement = date;
        }
    }
}