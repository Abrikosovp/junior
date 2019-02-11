package ru.shifu.servlets;

import java.util.Objects;
/**
 * Person
 *
 * @author Pavel Abrikosov(abrikosovp@mail.ru)
 * @version 0.1$
 * @since 0.1
 * 11.02.2019
 */
public class Person {
    private String firstname;
    private String secondname;
    private String sex;
    private String description;

    public Person() {
    }

    public Person(String firstname, String secondname, String sex, String description) {
        this.firstname = firstname;
        this.secondname = secondname;
        this.sex = sex;
        this.description = description;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getSecondname() {
        return secondname;
    }

    public void setSecondname(String secondname) {
        this.secondname = secondname;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return String.format("Person: {First name = %s; Second name = %s; Sex = %s; Description = %s}",
                firstname, secondname, sex, description);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Person person = (Person) o;
        return Objects.equals(firstname, person.firstname)
                && Objects.equals(secondname, person.secondname)
                && Objects.equals(sex, person.sex)
                && Objects.equals(description, person.description);
    }

    @Override
    public int hashCode() {
        return Objects.hash(firstname, secondname, sex, description);
    }
}