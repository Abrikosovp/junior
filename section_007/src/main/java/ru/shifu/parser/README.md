[![Build Status](https://travis-ci.org/Abrikosovp/junior.svg?branch=master)](https://travis-ci.org/Abrikosovp/junior)
[![codecov](https://codecov.io/gh/Abrikosovp/junior/branch/master/graph/badge.svg)](https://codecov.io/gh/Abrikosovp/junior)
# junior
Общее описание.

Приложение парсер заходит на сайт sql.ru в раздел работа и собирает Java вакансии.
Задача:
1. Реализованна модуль сборки анализа данных с sql.ru.
2. Система использует Jsoup для парсинга страниц.
3. Система запускаеться раз в день.
4. Система собирает данные только про вакансии java. учесть что JavaScript не подходит. как и Java Script.
5. Данные храняться в базе данных. 
В базе имеем таблицу vacancy (id, name, text, link)
id - первичный ключ
name - имя вакансии
text - текст вакансии
link - текст, ссылка на вакансию
6. Учитываются дубликаты. Вакансии с одинаковым именем считаются дубликатами.
7. Учитываются время последнего запуска. если это первый запуск. то собрать все объявления с начало года.
8. В системе нет вывода, либо ввода информации. все настройки файле. app.properties.
9. для вывода нужной информации использовать логгер log4j.
10. Пример запуска приложения.
java -jar SqlRuParser app.properties