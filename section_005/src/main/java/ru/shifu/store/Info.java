package ru.shifu.store;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
/**
 * Info.
 *
 * @author Pavel Abrikosov (abrikosovp@mail.ru).
 * @version 1.
 * @since 6.11.2018.
 **/
public class Info {
    /**
     * Счетчики изменений.
     */
    private int modified;
    private int remove;
    private int add;

    private List<Store.User> previous;
    private List<Store.User> current;

    private Map<Integer, String> prev;
    private Map<Integer, String> curr;

    public Info(List<Store.User> previous, List<Store.User> current) {
        this.previous = previous;
        this.current = current;
        this.modified = 0;
        this.remove = 0;
        this.add = 0;
        this.prev = toMap(previous);
        this.curr = toMap(current);
    }

    /**
     * Мето проверяет каждое условие на изменение.
     */
    public void changeCheck() {
        this.modified = (int) this.previous.stream().filter(x -> this.curr.containsKey(x.id) && !this.curr.get(x.id).equals(x.name)).count();
        this.add = (int) this.current.stream().filter(x -> !this.prev.containsKey(x.id)).count();
        this.remove = (int) this.previous.stream().filter(x -> !this.curr.containsKey(x.id)).count();
    }

    /**
     * Метод преобразует лист в мап.
     * @param map лист.
     * @return  мап.
     */
    private Map<Integer, String> toMap(List<Store.User> map) {
        return map.stream().collect(Collectors.toMap(x -> x.id, y -> y.name));
    }

    /**
     * Метод возвращает все модификации.
     * @return result.
     */
    public List<Integer> listModifications() {
        List<Integer> result = new ArrayList<>();
        result.add(modified);
        result.add(remove);
        result.add(add);
        return result;
    }
}
