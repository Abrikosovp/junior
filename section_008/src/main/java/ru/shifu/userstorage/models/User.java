package ru.shifu.userstorage.models;

import java.util.Date;

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
    private String login;
    private String password;
    private Role role;
    private PersonalData persona;
    private Date createDate;

    public User(String login, String password, Role role, PersonalData persona) {
        this.login = login;
        this.password = password;
        this.role = role;
        this.persona = persona;
        this.createDate = new Date();
    }

    public User(String id, String login, String password, Role role, PersonalData persona) {
        this.id = id;
        this.login = login;
        this.password = password;
        this.role = role;
        this.persona = persona;
        this.createDate = new Date();
    }

    public User(String id, String login, String password, Role role, PersonalData persona, Date date) {
        this.id = id;
        this.login = login;
        this.password = password;
        this.role = role;
        this.persona = persona;
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

    public String getLogin() {
        return login;
    }


    public Date getCreateDate() {
        return createDate;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return persona.getName();
    }

    public String getEmail() {
        return persona.getEmail();
    }

    public String getCountry() {
        return persona.getCountry();
    }

    public String getCity() {
        return persona.getCity();
    }

    @Override
    public String toString() {
        return String.format("User id: %s, Login: %s, Role: %s Create date: %s \n",
                id, login, role, createDate);
    }
}
