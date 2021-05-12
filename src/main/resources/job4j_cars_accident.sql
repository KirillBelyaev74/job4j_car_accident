create database job4j_cars_accident;

create table accident_type
(
    id   serial primary key,
    name varchar(50)
);

create table rule
(
    id   serial primary key,
    name varchar(50)
);

CREATE TABLE accident
(
    id               serial primary key,
    name             text,
    text             text,
    address          text,
    id_accident_type int references accident_type (id),
    id_rule          int references rule (id),
    constraint unta unique (name, text, address)
);

insert into accident_type(name) values ('Машина и велосипед');
insert into accident_type(name) values ('Машина и пешеход');
insert into accident_type(name) values ('Две машины');

insert into rule(name) values ('Статья №1');
insert into rule(name) values ('Статья №2');
insert into rule(name) values ('Статья №3');