package ru.shifu.search;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * PhoneDictionary имитирует справочник , и поиск человека в нем по адресу , тел и тд.
 *
 * @author Pavel Abrikosov (abrikosovp@mail.ru).
 * @version 1.
 * @since 14.10.2018.
 **/
public class PhoneDictionary {
    /**
     * Справочник в который мы добавляем person.
     */
    private List<Person> persons = new ArrayList<>();

    /**
     * Метод добавления нового человека в справочник.
     * @param person person.
     */
    public void add(Person person) {
        this.persons.add(person);
    }
    /**
     * Метод в совершает поиск в справочнике.
     * @param key ключ по которому будет иска (Фомилия, имя , адрес, тел. номер)
     * @return person.
     */
    public List<Person> find(String key) {
        return this.persons.stream()
                .filter(person -> person != null && findKey(person, key))
                .collect(Collectors.toList());
    }
    private boolean findKey(Person person, String key) {
        boolean result = false;
        if (person.getAddress().contains(key)
                || person.getName().contains(key)
                || person.getPhone().contains(key)
                || person.getSurname().contains(key)) {
            result = true;
        }
        return result;
    }
}
