create table if not exists items (
  id serial primary key not null,
  name varchar(200),
  description varchar(800)
);