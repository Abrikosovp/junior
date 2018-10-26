package ru.shifu.bank;

import java.util.*;
/**
 * bank.
 *
 * @author Pavel Abrikosov (abrikosovp@mail.ru).
 * @version 1.
 * @since 25.10.2018.
 **/
public class Bank {
    /**
     * Хранилище users и accounts.
     */
    Map<User, ArrayList<Account>> users = new TreeMap<>();

    /**
     * Метод отдает всех users.
     * @return users.
     */
    public Map<User, ArrayList<Account>> getUsers() {
        return this.users;
    }

    /**
     * Метод добавление пользователя.
     * @param user user.
     */
    public void addUser(User user) {
        this.users.putIfAbsent(user, new ArrayList<>());
    }

    /**
     * Метод - удаление пользователя.
     * @param user user.
     */
    public void deleteUser(User user) {
        this.users.remove(user);
    }

    /**
     * Метод - добавить счёт пользователю
     * @param passport passport.
     * @param account account.
     */
    public void addAccountToUser(String passport, Account account) {
        User user = this.findUser(passport);
        if (user != null) {
            this.users.get(user).add(account);
        }
    }

    /**
     * Метод - удалить один счёт пользователя.
     * @param passport passport.
     * @param account account.
     */
    public void deleteAccountFromUser(String passport, Account account) {
        User user = this.findUser(passport);
        if (user != null) {
            this.users.get(user).remove(account);
        }
    }

    /**
     * Метод - получить список счетов для пользователя.
     * @param passport passport
     * @return score.
     */
    public List<Account> getUserAccounts(String passport) {
        List<Account> score = new ArrayList<>();
        User user = this.findUser(passport);
        if (user != null) {
            score = this.users.get(user);
        }
        return score;
    }

    /**
     * Метод для перечисления денег с одного счёта на другой счёт:
     * если счёт не найден или не хватает денег на счёте srcAccount (с которого переводят) должен вернуть false.
     * @param srcPassport srcUser passport.
     * @param srcRequisite srcUser requisite from account.
     * @param destPassport destUser passport.
     * @param dstRequisite destUser requisite from account.
     * @param amount сумма для трансфера.
     * @return true если доступен перевод , иначе false
     */
    public boolean transferMoney(String srcPassport, String srcRequisite, String destPassport, String dstRequisite, double amount) {
        Account srcAccount = findAccount(srcPassport, srcRequisite);
        return srcAccount != null
                && this.transfer(findAccount(srcPassport, srcRequisite),
                findAccount(destPassport, dstRequisite),
                amount
        );
    }

    /**
     * Метод реализует перевод srcAccount на destAccount.
     * @param srcAccount account с которого переводим.
     * @param destAccount account на который переводим.
     * @param amount сумма.
     * @return true если все в порядке , false если есть недостатки.
     */
    public boolean transfer(Account srcAccount, Account destAccount, double amount) {
        boolean result = false;
        if (srcAccount.getValue() != 0 && srcAccount.getValue() >= amount && destAccount != null) {
            srcAccount.setValue(srcAccount.getValue() - amount);
            destAccount.setValue(destAccount.getValue() + amount);
            result = true;
        }
        return result;
    }

    /**
     * Метод проверяет существует ли account.
     * @param passport  passport.
     * @param requisite requisite.
     * @return true если account существует , true ели null
     */
    private Account findAccount(String passport, String requisite) {
       return this.getUserAccounts(passport).stream()
                .filter(account -> account.equals(new Account(0, requisite)))
                .findFirst()
                .orElse(null);
    }

    /**
     * Метод проверяет существует ли users.
     * @param passport passport.
     * @return true если user существует , true ели null
     */
    private User findUser(String passport) {
        return this.users.keySet().stream()
                .filter(user -> user != null && user.getPassport().equals(passport))
                .findFirst()
                .orElse(null);
    }
}
