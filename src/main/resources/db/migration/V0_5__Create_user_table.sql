create table "user"
(
    id       serial primary key,
    email    varchar unique not null,
    password varchar        not null,
    username varchar unique not null
)