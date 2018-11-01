package ru.shifu.list;
import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;
/**
 * NodeTest.
 *
 * @author Pavel Abrikosov (abrikosovp@mail.ru).
 * @version 1.
 * @since 1.11.2018.
 **/
public class NodeTest {
    Node first = new Node(1);
    Node two = new Node(2);
    Node third = new Node(3);
    Node four = new Node(4);

    @Before
    public void testBefore() {
        first.next = two;
        two.next = third;
        third.next = four;
        four.next = first;
    }

    @Test
    public void whenHasCycleReturnTrue() {
        assertThat(Node.hasCycle(first), is(true));
    }

    @Test
    public void whenHasCycleReturnTrueBy() {
        first = two;
        assertThat(Node.hasCycle(first), is(true));
    }

    @Test
    public void whenHasCycleReturnFalse() {
        first = null;
        assertThat(Node.hasCycle(first), is(false));
    }

    @Test
    public void whenNotCycleList() {
        four.next = null;
        boolean result = Node.hasCycle(first);
        assertThat(result, is(false));
    }
}
