create database car;

CREATE TABLE body (
  id serial primary key,
  name varchar(200)
);

CREATE TABLE engine (
  id SERIAL PRIMARY KEY,
  name VARCHAR(200)
);

CREATE TABLE gearbox (
  id SERIAL PRIMARY KEY,
  name VARCHAR(200)
);

CREATE TABLE car (
  id SERIAL PRIMARY KEY,
  name VARCHAR(200),
  body_id int NOT NULL REFERENCES body(id),
  engine_id int NOT NULL REFERENCES engine(id),
  gearbox_id int NOT NULL REFERENCES gearbox(id)
);

INSERT INTO body(name) VALUES ('sedan'), ('hatchback'), ('wagon'), ('coupe');
INSERT INTO engine(name) VALUES ('petrol'),('diesel'), ('electric'), ('dark matter');
INSERT INTO gearbox(name) VALUES ('manual6g'), ('manual4g'), ('robot'), ('variable speed drive');
INSERT INTO car(name, body_id, engine_id, gearbox_id) values
('car1', 1, 2, 3),
('car2', 2, 1, 2),
('car3', 3, 3, 1),
('car4', 1, 1, 1),
('car5', 2, 2, 2),
('car6', 3, 3, 3),
('car7', 3, 1, 1);


SELECT c.name, b.name, e.name, g.name FROM car as c
  INNER JOIN body b on c.body_id = b.id
  INNER JOIN engine e on c.engine_id = e.id
  INNER JOIN gearbox g on c.gearbox_id = g.id;

SELECT b.name FROM body as b
  LEFT OUTER JOIN car c on b.id = c.body_id
WHERE c.name is null;

SELECT e.name FROM engine as e
  LEFT OUTER JOIN car c ON e.id = c.engine_id
WHERE c.name is null;

SELECT g.name FROM gearbox as g
  LEFT OUTER JOIN car c ON g.id = c.gearbox_id
WHERE c.name isnull;