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
    constraint unta unique (name, text, address)
);

create table accident_rule
(
    id_accident int references accident (id),
    id_rule     int references rule (id)
);

CREATE TABLE authority
(
    id        serial primary key,
    authority VARCHAR(50) NOT NULL unique
);

CREATE TABLE users
(
    id           serial primary key,
    username     VARCHAR(50)  NOT NULL unique,
    password     VARCHAR(100) NOT NULL,
    enabled      boolean default true,
    authority_id int          not null references authority (id)
);