insert into accident_type(name)
values ('Машина и велосипед');
insert into accident_type(name)
values ('Машина и пешеход');
insert into accident_type(name)
values ('Две машины');

insert into rule(name)
values ('Статья №1');
insert into rule(name)
values ('Статья №2');
insert into rule(name)
values ('Статья №3');

insert into authority (authority)
values ('ROLE_USER');
insert into authority (authority)
values ('ROLE_ADMIN');

insert into users (username, password, authority_id)
values ('admin', '$2a$10$s2dFbOmNCUqXoQl6LjBg0unTyxUMlxqMUCQm5wev3/EPVr3y72x5q',
        (select id from authority where authority = 'ROLE_ADMIN'));

insert into users (username, password, authority_id)
values ('user', '$2a$10$Mpwd78psdMERocWcrxJ4g.IKuUlmJt9x4nV4x8fvfWi1Ma2nGT/wW',
        (select id from authority where authority = 'ROLE_USER'));