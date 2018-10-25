package ru.shifu.bank;

import java.util.Objects;
/**
 * Account.
 *
 * @author Pavel Abrikosov (abrikosovp@mail.ru).
 * @version 1.
 * @since 25.10.2018.
 **/
public class Account {
    /**
     * кол-во денег
     */
    private double value;
    /**
     * реквизиты счёта
     */
    private final String requisites;

    public Account(double value, String requisites) {
        this.value = value;
        this.requisites = requisites;
    }

    public void setValue(double value) {
        this.value = value;
    }

    public double getValue() {
        return value;
    }

    public String getRequisites() {
        return requisites;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Account account = (Account) o;
        return Objects.equals(requisites, account.requisites);
    }

    @Override
    public int hashCode() {
        return Objects.hash(requisites);
    }
}
