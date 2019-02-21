[![Build Status](https://travis-ci.org/Abrikosovp/junior.svg?branch=master)](https://travis-ci.org/Abrikosovp/junior)
[![codecov](https://codecov.io/gh/Abrikosovp/junior/branch/master/graph/badge.svg)](https://codecov.io/gh/Abrikosovp/junior)
# junior
Общее описание.

Реализованна коллекция Map для банка
Создан класс User с полями name, passport.
Добавлен методы eqauls() hashCode()
Создан класс Account с полями value (кол-во денег), requisites (реквизиты счёта) - это банковский счёт.
Реализованна коллекция Map <User, List<Account>>, обозначает что у каждого пользователя может быть список банковских счетов.
Реализованна возможность перечислять деньги, как с одного счёта User на другой счёт того же User, так и на счёт другого User.

В программе имеем методы:
public void addUser(User user) {} - добавление пользователя.
public void deleteUser(User user) {} - удаление пользователя.
public void addAccountToUser(String passport, Account account) {} - добавить счёт пользователю.
public void deleteAccountFromUser(String passport, Account account) {} - удалить один счёт пользователя.
public List<Accounts> getUserAccounts (String passport) {} - получить список счетов для пользователя.
public boolean transferMoney (String srcPassport, String srcRequisite, String destPassport, String dstRequisite, double amount) - метод для перечисления денег с одного счёта на другой счёт:
если счёт не найден или не хватает денег на счёте srcAccount (с которого переводят) должен вернуть false. Используем методы Map.putIfAbsent и List.indexOf.