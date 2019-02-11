package ru.shifu.servlets;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
/**
 * Person
 *
 * @author Pavel Abrikosov(abrikosovp@mail.ru)
 * @version 0.1$
 * @since 0.1
 * 11.02.2019
 */
public class PersonStorage {
    private static final PersonStorage STORAGE = new PersonStorage();
    private final ConcurrentHashMap<String, Person> store = new ConcurrentHashMap<>();

    private PersonStorage() {
    }

    public static PersonStorage getInstance() {
        return STORAGE;
    }

    public boolean add(Person person) {
        return store.put(person.getFirstname(), person) == null;
    }

    public List<Person> getAll() {
        List<Person> result = new ArrayList<>();
        result.addAll(store.values());
        return result;
    }
}