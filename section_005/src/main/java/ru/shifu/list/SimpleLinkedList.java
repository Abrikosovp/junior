package ru.shifu.list;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;
/**
 * SimpleLinkedList.
 *
 * @author Pavel Abrikosov (abrikosovp@mail.ru).
 * @version 1.
 * @since 30.10.2018.
 **/
public class SimpleLinkedList<E> implements Iterable<E> {

    private Node<E> head;
    private Node<E> tail;
    /**
     * Счетчик колличества элементов.
     */
    private int elementsCount;

    public SimpleLinkedList() {
        this.elementsCount = 0;
    }

    /**
     * Размер списка равен колличемтву элементов
     * @return размер.
     */
    public int size() {
        return this.elementsCount;
    }

    /**
     * Метод добавляет элемент в список.
     * @param value элемент который нужно добавить.
     */
    public void add(E value) {
        Node<E> first = this.tail;
        Node<E> newNode = new Node<>(first, value, null);
        this.tail = newNode;
        if (first == null) {
            this.head = newNode;
        } else {
            first.nextStep = newNode;
        }
        this.elementsCount++;
    }

    public void insertFirst(E value) {
        Node<E> first = head;
        Node<E> newNode = new Node<>(null, value, first);
        head = newNode;
        if (first == null) {
            tail = newNode;
        } else {
            first.preStep = newNode;
        }
        elementsCount++;
    }

    /**
     * Метод удаления первого элемента в списке.
     * @return элемент.
     */
    public E delete() {
        E result = null;
        if (this.size() != 0) {
            result = this.head.value;
            this.head = this.head.nextStep;
            this.elementsCount--;
        } else {
            result = null;
        }
        return result;
    }

    /**
     * Метод дастает элемент из списка.
     * @param index индекс элемента.
     * @return элемент.
     */
    public E get(int index) {
        if (index > this.elementsCount) {
            throw new NullPointerException(String.format("Index %s находиться за границей списка: (size) %s", index, this.size()));
        }

        Node<E> result = this.head;
        for (int i = 0; i < index; i++) {
            result = result.nextStep;
        }
        return result.value;
    }

    @Override
    public Iterator<E> iterator() {

        return new Iterator<E>() {
            private Node<E> result = null;
            private Node<E> nextInter = head;
            private int iterSize = 0;
            private int expectedModCount = elementsCount;

            @Override
            public boolean hasNext() {
                if (expectedModCount != elementsCount) {
                    throw new ConcurrentModificationException();
                }
                return iterSize != elementsCount;
            }

            @Override
            public E next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                iterSize++;
                result = nextInter;
                nextInter = nextInter.nextStep;
                return result.value;
            }
        };
    }

    private class Node<E> {
        E value;
        Node<E> nextStep;
        Node<E> preStep;

        public Node(Node<E> preStep, E currentElement, Node<E> nextStep) {
            this.nextStep = nextStep;
            this.preStep = preStep;
            this.value = currentElement;
        }
    }
}
