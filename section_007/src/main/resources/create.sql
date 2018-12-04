create database tracker;

create table rules (
    id serial primary key,
    name character(2000)
);

create table role (
    id serial primary key,
    name character(2000)
);

create table role_rules_compose (
    id serial primary key,
    role_id integer references role(id),
    rules_id integer references rules(id)
);

create table users
(
    id      serial primary key,
    name    character(200),
    surname character(200),
    role_id integer references role(id)
);

create table categories (
    id serial primary key,
    category character(200)
);

create table states (
    id serial primary key,
    state character(200)
);

create table items (
    id serial primary key,
    name character(200),
    author_id integer references users(id),
    states_id integer references states(id),
    categories_id integer references categories(id),
    descriptions text
);

create table comments (
    id serial primary key,
    name character(200),
    user_id integer references users(id),
    item_id integer references items(id),
    description text
);

create table attachments (
    id serial primary key,
    item_id integer references items(id),
    name character(500),
    patch character(500),
    description text
);

insert into role(name) values('admin');
insert into role(name) values('user');
insert into role(name) values('guest');

insert into rules(name) values('full rules');
insert into rules(name) values('reading');
insert into rules(name) values('writing');

insert into role_rules_compose(role_id, rules_id) values(1, 1);
insert into role_rules_compose(role_id, rules_id) values(1, 2);
insert into role_rules_compose(role_id, rules_id) values(1, 3);
insert into role_rules_compose(role_id, rules_id) values(2, 2);
insert into role_rules_compose(role_id, rules_id) values(2, 3);
insert into role_rules_compose(role_id, rules_id) values(3, 2);

insert into users(name, surname, role_id) values('Jon', 'Bah', 3);
insert into users(name, surname, role_id) values('Lemon', 'Match', 2);
insert into users(name, surname, role_id) values('Pavel', 'Man', 1);

insert into categories(category) values('development');
insert into categories(category) values('refactoring');
insert into categories(category) values('devops');

insert into states(state) values('new');
insert into states(state) values('in work');
insert into states(state) values('close');

insert into items(name, categories_id, descriptions, author_id, states_id) values('Подключить бд', 1, 'Настроить бд', 1, 1);
insert into items(name, categories_id, descriptions, author_id, states_id) values('Создать бд', 2, 'Содать новую бд', 2, 2);
insert into items(name, categories_id, descriptions, author_id, states_id) values('Залить на git', 3, 'Скрипт залейте в файл create.sql и залей его в репозиторий', 3, 3);

insert into attachments(item_id, name, patch, description) values(1, 'uml.uml', '/project/tracker/uml.uml', 'UML диаграмма классов');