package ru.shifu.bank;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;
/**
 * BankTest.
 *
 * @author Pavel Abrikosov (abrikosovp@mail.ru).
 * @version 1.
 * @since 25.10.2018.
 **/
public class BankTest {
    /**
     * Тест addUser.
     */
    @Test
    public void whenAddUser() {
        Bank bank = new Bank();
        bank.addUser(new User("Павел", "0001 000000"));
        assertThat(bank.getUsers().size(), is(1));
    }
    /**
     * Тест deleteUser.
     */
    @Test
    public void whenDeleteUser() {
        Bank bank = new Bank();
        User user = new User("Павел", "0001 000000");
        bank.addUser(user);
        bank.deleteUser(user);
        assertThat(bank.getUsers().isEmpty(), is(true));
    }
    /**
     * Тест addAccountToUser.
     */
    @Test
    public void whenAddAccountToUser() {
        Bank bank = new Bank();
        User user = new User("Павел", "0001 000000");
        bank.addUser(user);
        bank.addAccountToUser("0001 000000", new Account(0, "22222222222222222"));
        assertThat(bank.getUsers().get(user).size(), is(1));
    }
    /**
     * Тест deleteAccountFromUser.
     */
    @Test
    public void whenDeleteAccountFromUser() {
        Bank bank = new Bank();
        User user = new User("Павел", "0001 000000");
        bank.addUser(user);
        Account account = new Account(0, "22222222222222222");
        bank.addAccountToUser(user.getPassport(), account);
        bank.deleteAccountFromUser(user.getPassport(), account);
        assertThat(bank.getUsers().get(user).isEmpty(), is(true));
    }
    /**
     * Тест transferMoney.
     */
    @Test
    public void whenTransferMoney() {
        Bank bank = new Bank();
        User user = new User("Павел", "0001 000000");
        User secondUser = new User("Ivan", "0002 000013");
        bank.addUser(user);
        bank.addUser(secondUser);
        Account account = new Account(500, "22222222222222222");
        Account accountIvan = new Account(300, "3333333333333333");
        bank.addAccountToUser(user.getPassport(), account);
        bank.addAccountToUser(secondUser.getPassport(), accountIvan);
        boolean rst = bank.transferMoney(
                user.getPassport(),
                account.getRequisites(),
                secondUser.getPassport(),
                accountIvan.getRequisites(),
                400
        );
        assertThat(rst, is(true));
    }
    /**
     * Тест transferMoney.
     */
    @Test
    public void whenInvalidAmount() {
        Bank bank = new Bank();
        User user = new User("Павел", "0001 000000");
        User secondUser = new User("Ivan", "0002 000013");
        bank.addUser(user);
        bank.addUser(secondUser);
        Account account = new Account(500, "22222222222222222");
        Account accountIvan = new Account(300, "3333333333333333");
        bank.addAccountToUser(user.getPassport(), account);
        bank.addAccountToUser(secondUser.getPassport(), accountIvan);
        boolean rst = bank.transferMoney(
                user.getPassport(),
                account.getRequisites(),
                secondUser.getPassport(),
                accountIvan.getRequisites(),
                600
        );
        assertThat(rst, is(false));
    }
    /**
     * Тест transferMoney.
     */
    @Test
    public void whenInvalidSrcAccount() {
        Bank bank = new Bank();
        User user = new User("Павел", "0001 000000");
        User secondUser = new User("Ivan", "0002 000013");
        bank.addUser(user);
        bank.addUser(secondUser);
        Account account = new Account(500, "22222222222222222");
        Account accountIvan = new Account(300, "3333333333333333");
        bank.addAccountToUser(user.getPassport(), account);
        bank.addAccountToUser(secondUser.getPassport(), accountIvan);
        boolean rst = bank.transferMoney(
                user.getPassport(),
                "22222222222222223",
                secondUser.getPassport(),
                accountIvan.getRequisites(),
                400
        );
        assertThat(rst, is(false));
    }
    /**
     * Тест transferMoney.
     */
    @Test
    public void whenInvalidDestAccount() {
        Bank bank = new Bank();
        User user = new User("Павел", "0001 000000");
        User secondUser = new User("Ivan", "0002 000013");
        bank.addUser(user);
        bank.addUser(secondUser);
        Account account = new Account(500, "22222222222222222");
        Account accountIvan = new Account(300, "3333333333333333");
        bank.addAccountToUser(user.getPassport(), account);
        bank.addAccountToUser(secondUser.getPassport(), accountIvan);
        boolean rst = bank.transferMoney(
                user.getPassport(),
                "22222222222222222",
                secondUser.getPassport(),
                "3333333333333334",
                400
        );
        assertThat(rst, is(false));
    }
    /**
     * Тест transferMoney.
     */
    @Test
    public void whenInvalidSrcPassportAmount() {
        Bank bank = new Bank();
        User user = new User("Павел", "0001 000000");
        User secondUser = new User("Ivan", "0002 000013");
        bank.addUser(user);
        bank.addUser(secondUser);
        Account account = new Account(500, "22222222222222222");
        Account accountIvan = new Account(300, "3333333333333333");
        bank.addAccountToUser(user.getPassport(), account);
        bank.addAccountToUser(secondUser.getPassport(), accountIvan);
        boolean rst = bank.transferMoney(
                "0001 000002",
                account.getRequisites(),
                secondUser.getPassport(),
                accountIvan.getRequisites(),
                200
        );
        assertThat(rst, is(false));
    }
    /**
     * Тест transferMoney.
     */
    @Test
    public void whenInvalidDestPassportAmount() {
        Bank bank = new Bank();
        User user = new User("Павел", "0001 000000");
        User secondUser = new User("Ivan", "0002 000013");
        bank.addUser(user);
        bank.addUser(secondUser);
        Account account = new Account(500, "22222222222222222");
        Account accountIvan = new Account(300, "3333333333333333");
        bank.addAccountToUser(user.getPassport(), account);
        bank.addAccountToUser(secondUser.getPassport(), accountIvan);
        boolean rst = bank.transferMoney(
                user.getPassport(),
                account.getRequisites(),
                "0002 000015",
                accountIvan.getRequisites(),
                200
        );
        assertThat(rst, is(false));
    }
}
