package ru.shifu.search;
import org.junit.Test;

import java.util.List;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;
/**
 * PhoneDictionaryTest имитирует справочник , и поиск человека в нем по адресу , тел и тд.
 *
 * @author Pavel Abrikosov (abrikosovp@mail.ru).
 * @version 1.
 * @since 14.10.2018.
 **/
public class PhoneDictionaryTest {
    /**
     * Тест поиск по имени
     */
    @Test
    public void whenFindByName() {
    PhoneDictionary phones = new PhoneDictionary();
    phones.add(new Person("Pavel", "Abrikosov", "966", "msc"));
    List<Person> people = phones.find("Pavel");
    assertThat(people.iterator().next().getPhone(), is("966"));
    }
    /**
     * Тест поиск по номеру тел.
     */
    @Test
    public void whenFindByPhone() {
        PhoneDictionary phones = new PhoneDictionary();
        phones.add(new Person("Pavel", "Abrikosov", "966", "msc"));
        List<Person> people = phones.find("966");
        assertThat(people.iterator().next().getPhone(), is("966"));
    }
    /**
     * Тест поиск по адресу.
     */
    @Test
    public void whenFindByAddress() {
        PhoneDictionary phones = new PhoneDictionary();
        phones.add(new Person("Pavel", "Abrikosov", "966", "msc"));
        List<Person> people = phones.find("msc");
        assertThat(people.iterator().next().getPhone(), is("966"));
    }
    /**
     * Тест поиск по фомлии.
     */
    @Test
    public void whenFindBySurname() {
        PhoneDictionary phones = new PhoneDictionary();
        phones.add(new Person("Pavel", "Abrikosov", "966", "msc"));
        List<Person> people = phones.find("Abrikosov");
        assertThat(people.iterator().next().getPhone(), is("966"));
    }
}
