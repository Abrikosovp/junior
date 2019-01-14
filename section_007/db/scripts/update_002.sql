CREATE table if not exists vacancies(
id serial primary key,
header character varying(2000),
href text,
date Timestamp
);