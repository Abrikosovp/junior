package ru.shifu.list;
/**
 * Node.
 *
 * @author Pavel Abrikosov (abrikosovp@mail.ru).
 * @version 1.
 * @since 1.11.2018.
 **/
public class Node<T> {
    T value;
    Node<T> next;

    public Node(T value) {
        this.value = value;
    }

    /**
     * Метод кролик и черепаха:
     * Создаем 2 Node , запускаем цикл
     * первый Node итет на 1 шаг , второй идет на 2 шага ,
     * если есть зацикленность то они выравниваютя.
     * @param first first
     * @return true если есть зацикленность.
     */
    public static boolean hasCycle(Node first) {
        boolean result = false;

        Node turtle = first;
        Node rabbit = first;

        while (rabbit != null && rabbit.next != null) {

            turtle = turtle.next;
            rabbit = rabbit.next.next;

            if (turtle == rabbit) {
                result = true;
                break;
            }
        }
        return result;
    }
}
