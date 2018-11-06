package ru.shifu.tree;

import java.util.ArrayList;
import java.util.List;
/**
 * Node.
 *
 * @author Pavel Abrikosov (abrikosovp@mail.ru).
 * @version 1.
 * @since 5.11.2018.
 **/
public class Node<E extends Comparable<E>> {
    /**
     * Хранилище для child, у каждого Node он свой.
     */
   private final List<Node<E>> children = new ArrayList<>();
    /**
     * child
     */
   private final E value;

    public Node(E value) {
        this.value = value;
    }

    /**
     * Метод добавляет новый child в список children
     * @param value child.
     */
    public void add(Node<E> value) {
        if (!children.contains(value)) {
            this.children.add(value);
        }
    }

    /**
     * Метод отдает список.
     * @return children.
     */
    public List<Node<E>> leaves() {
        return this.children;
    }

    /**
     * Метод сравнивает value
     * @param that value
     * @return true / false
     */
    public boolean qvValue(E that) {
        return this.value.compareTo(that) == 0;
    }

    /**
     * @return value.
     */
    public E getValue() {
        return value;
    }

    public boolean contains(E childValue) {
        return children.stream().noneMatch(value -> value.value.equals(childValue));
    }
}
