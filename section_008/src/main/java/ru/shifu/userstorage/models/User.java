package ru.shifu.userstorage.models;

import java.util.Date;
import java.util.Random;

/**
 * Simple model of data.
 *
 * @author Pavel Abrikosov (abrikosovp@mail.ru)
 * @version 0.5$
 * @since 0.1
 * 18.01.2019
 */
public class User {
    private String id;
    private String name;
    private String login;
    private String password;
    private Role role;
    private String email;
    private Date createDate;

    public User(String name, String login, String password, Role role, String email) {
        this.name = name;
        this.login = login;
        this.password = password;
        this.role = role;
        this.email = email;
        this.createDate = new Date();
    }

    public User(String id, String name, String login, String password, Role role, String email) {
        this.id = id;
        this.name = name;
        this.login = login;
        this.password = password;
        this.role = role;
        this.email = email;
        this.createDate = new Date();
    }

    public User(String id, String name, String login, String password, Role role, String email, Date date) {
        this.id = id;
        this.name = name;
        this.login = login;
        this.password = password;
        this.role = role;
        this.email = email;
        this.createDate = date;
    }

    public String getPassword() {
        return password;
    }

    public Role getRole() {
        return role;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getLogin() {
        return login;
    }

    public String getEmail() {
        return email;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return String.format("User id: %s, Name: %s, Login: %s, Role: %s Email: %s, Create date: %s \n",
                id, name, login, role, email, createDate);
    }


}
