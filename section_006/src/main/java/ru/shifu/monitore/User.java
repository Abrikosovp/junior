package ru.shifu.monitore;

import java.util.Objects;
/**
 * User.
 *
 * @author Pavel Abrikosov (abrikosovp@mail.ru).
 * @version 1.
 * @since 20.11.2018.
 **/
public class User {
    /**
     * id user.
     */
    private int id;
    /**
     * колличество дененг на счету.
     */
    private int amount;

    public User(int id) {
        this.id = id;
    }

    public User(int id, int amount) {
        this.id = id;
        this.amount = amount;
    }

    /**
     * Метод переводит средства со счета на счет.
     * @param userTo Юзер которому нужно осуществить перевод.
     * @param amount сумму которую переводим
     * @return  true and false
     */
    protected boolean transfer(User userTo, int amount) {
        boolean result = false;
        if (this.amount >= amount && userTo != null) {
            this.amount -= userTo.amount;
            userTo.amount += amount;
            result = true;
        }
        return result;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        User user = (User) o;
        return id == user.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
