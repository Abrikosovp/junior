package ru.shifu.search;

import java.util.ArrayList;
import java.util.List;

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
        List<Person> result = new ArrayList<>();
        for (Person person : this.persons) {
            if (person.getAddress().contains(key)
            || person.getName().contains(key)
            || person.getPhone().contains(key)
            || person.getSurname().contains(key)) {
                result.add(person);
                break;
            }
        }
        return result;
    }
}
