[![Build Status](https://travis-ci.org/Abrikosovp/junior.svg?branch=master)](https://travis-ci.org/Abrikosovp/junior)
[![codecov](https://codecov.io/gh/Abrikosovp/junior/branch/master/graph/badge.svg)](https://codecov.io/gh/Abrikosovp/junior)
# junior

Общее описание.
Реализовали простой веб сайт по покупки билетов в кинотеатр.
Начальная страница сайта index.html. 
На ней мы показываем Зал c Рядами.
1. Данные на index.html загружаются через  Ajax. 
2. Для этого создайте Сервлет HallServlet. 
3. Если место занято, то нужно это отображать в таблице.
4. Страница обновляется периодически через timout.
5. После того, как пользователь выбрал место переходим на страницу payment.html. Делается это через window.local.href.
На странице нужно указать место и сумму денег.
6. Имеем три слоя. Controller, Service, Persistence.
7. Иммеем две таблицы. halls, accounts. 
8. В Persistence используйте jdbc. Важно. что записи выполняеться атомарно. То есть в одной транзакции - https://examples.javacodegeeks.com/core-java/sql/jdbc-nested-transactions-example/ 





