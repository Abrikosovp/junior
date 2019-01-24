package ru.shifu.userstorage.persistent;

import java.util.Date;
import java.util.Random;

/**
 * Simple model of data.
 *
 * @author Pavel Abrikosov (abrikosovp@mail.ru)
 * @version 0.3$
 * @since 0.1
 * 18.01.2019
 */
public class User {
    private String id;
    private String name;
    private String login;
    private String email;
    private Date createDate;

    /**
     * To generate a random id.
     */
    private final Random rn = new Random();

    public User(String name, String login, String email) {
        this.id = String.valueOf(System.currentTimeMillis() + rn.nextInt());
        this.name = name;
        this.login = login;
        this.email = email;
        this.createDate = new Date();
    }

    public User(String id, String name, String login, String email) {
        this.id = id;
        this.name = name;
        this.login = login;
        this.email = email;
        this.createDate = new Date();
    }

    public User(String id, String name, String login, String email, Date date) {
        this.id = id;
        this.name = name;
        this.login = login;
        this.email = email;
        this.createDate = date;
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
        return  createDate;
    }

    @Override
    public String toString() {
        return String.format("User id: %s, Name: %s, Login: %s, Email: %s, Create date: %s \n",
                id, name, login, email, createDate);
    }


}
