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
        rsl.ifPresent(i -> {
            i.add(new Node<>(child));
            result.set(true);
            modCount++;
        });
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

    @Override
    public Iterator<E> iterator() {
        return new NewIterator(this.root);
    }

    private class NewIterator<E extends Comparable<E>> implements Iterator<E> {
        /**
         * Корень
         */
        private final Node<E> root;
        /**
         * Хранилище временное.
         */
        private final Queue<Node<E>> inDate = new LinkedList<>();
        private final int expectedModCount = modCount;

        NewIterator(Node<E> root) {
            this.root = root;
            initialization();
        }

        @Override
        public boolean hasNext() {
            if (this.expectedModCount != modCount) {
                throw new ConcurrentModificationException();
            }
            return !this.inDate.isEmpty();
        }

        /**
         * Метод отгружает все элементы из дерева в idDate.
         */
        private void initialization() {
            Queue<Node<E>> data = new LinkedList<>();
            data.offer(this.root);
            while (!data.isEmpty()) {
                Node<E> el = data.poll();
                inDate.offer(el);

                for (Node<E> child : el.leaves()) {
                    data.offer(child);
                }
            }
        }

        @Override
        public E next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            return inDate.poll().getValue();
        }
    }
}
