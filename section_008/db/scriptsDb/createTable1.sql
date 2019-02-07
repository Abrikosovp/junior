CREATE TABLE IF NOT EXISTS userses (
id serial primary key,
name VARCHAR(50),
login VARCHAR(50) NOT NULL,
password VARCHAR(50) NOT NULL,
role VARCHAR(10),
email VARCHAR(50),
created DATE);