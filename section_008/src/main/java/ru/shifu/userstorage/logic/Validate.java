package ru.shifu.userstorage.logic;

import ru.shifu.userstorage.models.User;

import java.util.List;

public interface Validate {

    String doAction(final Action.Type action, final User user);

    List<User> findAll();

    User findById(String id);

    long isRegistered(String login, String password);

    boolean fullDelete();

    List<String> getCountries();

    List<String> getCities(String country);
}
