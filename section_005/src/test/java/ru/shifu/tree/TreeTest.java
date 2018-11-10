package ru.shifu.tree;
import org.junit.Test;
import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;
/**
 * TreeTest.
 *
 * @author Pavel Abrikosov (abrikosovp@mail.ru).
 * @version 1.
 * @since 5.11.2018.
 **/
public class TreeTest {

    @Test
    public void when6ElFindLastThen6() {
        Tree<Integer> tree = new Tree<>(1);
        tree.add(1, 2);
        tree.add(1, 3);
        tree.add(1, 3);
        tree.add(1, 4);
        tree.add(4, 5);
        tree.add(1, 4);
        tree.add(5, 6);
        assertThat(
                tree.findBy(6).isPresent(),
                is(true)
        );
    }

    @Test
    public void when6ElFindLastThen6ByLeavesSizeLessOrEqualsTwo() {
        Tree<Integer> tree = new Tree<>(1);
        tree.add(1, 2);
        tree.add(1, 3);
        tree.add(4, 5);
        tree.add(4, 5);
        tree.add(5, 6);
        assertThat(tree.isBinary(), is(true));
    }
    @Test
    public void whenBinaryTreeThenFalse() {
        Tree<Integer> tree = new Tree<>(1);
        tree.add(1, 2);
        tree.add(1, 3);
        tree.add(1, 1);
        tree.add(3, 4);
        tree.add(4, 5);
        tree.add(5, 6);
        assertThat(tree.isBinary(), is(true));
    }

    @Test
    public void when6ElFindNotExitThenOptionEmpty() {
        Tree<Integer> tree = new Tree<>(1);
        tree.add(1, 2);
        assertThat(
                tree.findBy(7).isPresent(),
                is(false)
        );
    }

    /**
     * Тест iterator.
     */
    @Test
    public void hasNextSequentialInvocation() {
        Tree<Integer> tree = new Tree<>(1);
        tree.add(1, 2);
        tree.add(1, 3);
        tree.add(1, 4);
        tree.add(4, 5);
        tree.add(5, 6);
        Iterator<Integer> it = tree.iterator();
        assertThat(it.hasNext(), is(true));
        assertThat(it.next(), is(1));
        assertThat(it.hasNext(), is(true));
        assertThat(it.next(), is(2));
        assertThat(it.hasNext(), is(true));
        assertThat(it.next(), is(3));
        assertThat(it.hasNext(), is(true));
        assertThat(it.next(), is(4));
        assertThat(it.hasNext(), is(true));
        assertThat(it.next(), is(5));
        assertThat(it.hasNext(), is(true));
        assertThat(it.next(), is(6));
        assertThat(it.hasNext(), is(false));
    }

    /**
     * Тест iterator.
     */
    @Test(expected = NoSuchElementException.class)
    public void shouldThrowNoSuchElementException() {
        Tree<Integer> tree = new Tree<>(1);
        Iterator<Integer> it = tree.iterator();
        it.next();
        it.next();
    }

    /**
     * Тест iterator().
     */
    @Test(expected = ConcurrentModificationException.class)
    public void shouldConcurrentModificationException() {
        Tree<Integer> tree = new Tree<>(1);
        Iterator<Integer> it = tree.iterator();
        tree.add(1, 2);
        it.next();
    }
}