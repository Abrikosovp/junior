package ru.shifu.monitore;

import net.jcip.annotations.GuardedBy;
import net.jcip.annotations.ThreadSafe;

import java.util.HashMap;
/**
 * UserStorage.
 *
 * @author Pavel Abrikosov (abrikosovp@mail.ru).
 * @version 1.
 * @since 20.11.2018.
 **/
@ThreadSafe
public class UserStorage {
    @GuardedBy("this")
    private HashMap<User, User> data;

    public UserStorage() {
        this.data = new HashMap<>();
    }

    /**
     * Метод добавляет нового пользователя.
     * @param user пользователь.
     * @return true and false
     */
    public synchronized boolean add(User user) {
        boolean result = false;
        if (!this.data.containsKey(user)) {
            this.data.put(user, user);
            result = true;
        }
        return result;
    }

    /**
     * Метод заменить пользователя.
     * @param user новый пользователь
     * @return  true and false
     */
   public synchronized boolean update(User user) {
        return this.data.replace(user, user) != null;
   }

    /**
     * Метод удаляет счет и пользователя.
     * @param user пользователь.
     * @return true and false
     */
    public synchronized boolean delete(User user) {
        return this.data.replace(user, user) != null;
    }

    /**
     * Метод совершает перевод денежных средств со счета на счет.
     * @param fromId с которого счета совершается перевод.
     * @param toId на который счет совершается перевод.
     * @param amount сумма перевода.
     * @return true and false
     */
   public synchronized boolean transfer(int fromId, int toId, int amount) {
        boolean result = false;
        User from = this.data.get(new User(fromId));
        User to = this.data.get(new User(toId));
        if (from != null && to != null) {
            synchronized (from) {
                synchronized (to) {
                    result = from.transfer(to, amount);
                }
            }
        }
        return result;
    }
}
