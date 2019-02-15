package ru.shifu.userstorage.models;
/**
 * The class stores user personal data.
 *
 * @author Pavel Abrikosov (abrikosovp@mail.ru)
 * @version 0.5$
 * @since 0.1
 * 18.01.2019
 */
public class PersonalData {

    private String name;
    private String email;
    private String country;
    private String city;

    public PersonalData(String name, String email, String country, String city) {
        this.name = name;
        this.email = email;
        this.country = country;
        this.city = city;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getCountry() {
        return country;
    }

    public String getCity() {
        return city;
    }

    @Override
    public String toString() {
        return String.format("Personal data {name: %s, email: %s, country: %s, city: %s}", name, email, country, city);
    }
}
