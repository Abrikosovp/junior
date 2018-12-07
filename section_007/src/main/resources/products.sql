CREATE DATABASE products;

create table type (
    id serial primary key,
    name character(200)
);

create table product (
    id serial primary key,
    name character(100),
    type_id integer references type(id),
    expired_date timestamp,
    price integer
);

insert into type(name) values('Молоко'),
                             ('Сыр'),
                             ('Мороженное');

insert into product(name, type_id, expired_date, price)
values
('Пармизан', '2', '2019-01-08 04:05:06', '2340'),
('Моцарелла', '2', '2018-12-28 00:05:06', '800'),
('Горгонзола', '2', '2019-5-18 21:45:06', '1300'),
('Соевое', '1', '2018-12-10 02:45:06', '80'),
('Козье', '1', '2018-08-10 12:45:06', '120'),
('Снежная королева', '3', '2018-11-1 2:45:06', '43'),
('Белый медведь', '3', '2018-12-1 2:45:06', '51'),
('Большой папа', '3', '2018-11-8 18:45:06', '65'),
('Овсяное', '1', '2018-12-08 11:30:06', '89'),
('Какосовое', '1', '2018-12-08 11:30:06', '89');

-- Написать запрос получение всех продуктов с типом "СЫР"
SELECT * FROM product
WHERE type_id = (SELECT id FROM type where name = 'Сыр');

-- Написать запрос получения всех продуктов, у кого в имени есть слово "мороженное"
SELECT * FROM product
WHERE type_id = (SELECT id FROM type WHERE name like '%Морож%');

-- Написать запрос, который выводит все продукты, срок годности которых заканчивается в следующем месяце.
SELECT * FROM product
WHERE (SELECT date_part('month', expired_date)) = (date_part('month', INTERVAL '1 month'));

-- Написать запрос, который выводит самый дорогой продукт.
-- #1
SELECT name, price FROM product
WHERE price = (SELECT max(price) from product);
--#2
SELECT name, MAX(price) FROM product
GROUP BY name
ORDER BY MAX(price) DESC
LIMIT 1;

-- Написать запрос, который выводит количество всех продуктов определенного типа.
SELECT t.name, COUNT(type_id)FROM product as p
INNER JOIN type as t
ON p.type_id = t.id
GROUP BY t.name;

-- Написать запрос получение всех продуктов с типом "СЫР" и "МОЛОКО"
SELECT * FROM product
WHERE type_id = (SELECT id FROM type WHERE type.name = 'Сыр')
   or
    type_id = (SELECT id FROM type WHERE type.name = 'Молоко');

-- Написать запрос, который выводит тип продуктов, которых осталось меньше 10 штук.
SELECT t.name FROM product as p
INNER JOIN type as t
ON p.type_id = t.id
GROUP BY t.name
HAVING count(type_id) <= 10;

-- Вывести все продукты и их тип.
SELECT * FROM product as p
INNER JOIN type as t on p.type_id = t.id
ORDER BY p.price DESC;