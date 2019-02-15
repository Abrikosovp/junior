DROP TABLE IF EXISTS userses;
DROP TABLE IF EXISTS cities;
DROP TABLE IF EXISTS countries;
CREATE TABLE countries (
c_id serial,
name VARCHAR(50) UNIQUE,
PRIMARY KEY(c_id));
CREATE TABLE cities (
loc_id serial PRIMARY KEY,
c_id INTEGER REFERENCES countries(c_id),
name VARCHAR(50) UNIQUE);
CREATE TABLE userses(
id serial PRIMARY KEY,
name VARCHAR(50),
login VARCHAR(50) NOT NULL,
password VARCHAR(50) NOT NULL,
role VARCHAR(10),
email VARCHAR(50),
loc_id INTEGER REFERENCES cities(loc_id),
created DATE);
INSERT INTO countries (name) VALUES
('Russia'),
('Germany'),
('USA');
INSERT INTO cities (c_id, name) VALUES
(1, 'Moscow'),
(1, 'Saint-Petersburg'),
(1, 'Krasnodar'),
(2, 'Berlin'),
(2, 'Hamburg'),
(3, 'New York'),
(3, 'Washington'),
(3, 'California');