insert into company(id, name) values('5', 'Google'), ('1','Yandex'), ('2', 'Apple');
insert into person(id, name, company_id) values('11','Bob', '1'), ('12','Jon', '1'), ('13','Ben', '1'), ('14','Peter', '1'),
                                                ('21','Евгений', '5'), ('22','Павел', '5'), ('23','Карина', '5'), ('24','Анастасия', '5'),
                                                ('25','Антон', '5'), ('26','Рита', '5'), ('27','Маргарита', '5'), ('28','Максим', '5'),
                                                ('31','Bruce', '2'), ('32','Maggi', '2');

-- 1) Select the name of the company with the maximum number of persons + number of persons in this company
select c.name , count(*) as summ from person p
inner join company c
on (p.company_id = c.id)
group by c.name
order by summ DESC
limit 1;

--  2) Retrieve in a single query:
--  - names of all persons that are NOT in the company with id = 5
--  - company name for each person
select p.name, c.name from company c
inner join person p
on (c.id = p.company_id)
where c.id != 5;