package ru.shifu.tree;

import java.util.Optional;
/**
 * SimpleTree.
 *
 * @author Pavel Abrikosov (abrikosovp@mail.ru).
 * @version 1.
 * @since 5.11.2018.
 **/
public interface SimpleTree<E extends Comparable<E>> extends Iterable<E> {

    boolean add(E parent, E child);
    Optional<Node<E>> findBy(E parent);
}
