package ru.shifu.tree;

import java.util.*;
import java.util.concurrent.atomic.AtomicBoolean;
/**
 * Tree.
 *
 * @author Pavel Abrikosov (abrikosovp@mail.ru).
 * @version 1.
 * @since 5.11.2018.
 **/
public class Tree<E extends Comparable<E>> implements SimpleTree<E> {
    /**
     * Корень.
     */
    private final Node<E> root;
    /**
     * Счетчик
     */
    private int modCount;

    public Tree(E value) {
        this.root = new Node<>(value);
    }

    /**
     * Метод добовляет элемент в дерево.
     * @param parent parent.
     * @param child child.
     * @return child.
     */
    @Override
    public boolean add(E parent, E child) {
        AtomicBoolean result = new AtomicBoolean(false);
        Optional<Node<E>> rsl = this.findBy(parent);
        if (find(child)) {
            rsl.ifPresent(i -> {
                i.add(new Node<>(child));
                result.set(true);
                modCount++;
            });
        }
        return result.get();
        }

    /**
     * Метод для поиска элементов в дереве.
     * @param value parent.
     * @return child.
     */
    @Override
    public Optional<Node<E>> findBy(E value) {
        Optional<Node<E>> rsl = Optional.empty();
        Queue<Node<E>> data = new LinkedList<>();
        data.offer(this.root);
        while (!data.isEmpty()) {
            Node<E> el = data.poll();
            if (el.qvValue(value)) {
                rsl = Optional.of(el);
            }
            for (Node<E> child : el.leaves()) {
                data.offer(child);
            }
        }
        return rsl;
    }

    /**
     * Метод проверяет что никакие узлы в дереве
     * не должны иметь двух одинаковых дочерних узлов.
     * @param childValue
     * @return true / false
     */
    public boolean find(E childValue) {
        boolean result = true;
        Queue<Node<E>> data = new LinkedList<>();
        data.offer(this.root);
        while (!data.isEmpty()) {
            Node<E> el = data.poll();
            if (!el.contains(childValue)) {
               result = false;
               break;
            }
            for (Node<E> child : el.leaves()) {
                data.offer(child);
            }
        }
        return result;
    }

    /**
     * Метод проверяет количество дочерних элементов в дереве
     * они не должны превышать 2х
     * @return true / false
     */
    public boolean isBinary() {
        boolean result = true;
        Queue<Node<E>> data = new LinkedList<>();
        data.offer(this.root);
        while (!data.isEmpty()) {
            Node<E> el = data.poll();
            if (el != null && el.leaves().size() > 2) {
                result = false;
                break;
            }
            for (Node<E> child : el.leaves()) {
                data.offer(child);
            }
        }
        return result;
    }

    @Override
    public Iterator<E> iterator() {

        return new Iterator<E>() {

            /**
             * Хранилище временное.
             */
            private final LinkedList<Node<E>> inDate = new LinkedList<>(Collections.singletonList(root));
            private final int expectedModCount = modCount;


            /**
             * Метод проверяет что список не пуст.
             * @return true / false
             */
            @Override
            public boolean hasNext() {
                if (this.expectedModCount != modCount) {
                    throw new ConcurrentModificationException();
                }
                return !inDate.isEmpty();
            }

            /**
             * Метод достает значение поэлементно из дерва.
             * @return value.
             */
            @Override
            public E next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                E elem = null;
                if (inDate.size() != 0) {
                    Node<E> firstElem = inDate.pollFirst();
                    inDate.addAll(firstElem.leaves());
                    elem = firstElem.getValue();
                }
                return elem;
            }
        };
    }
}
