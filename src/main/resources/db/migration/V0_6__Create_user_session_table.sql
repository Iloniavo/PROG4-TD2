create table user_session (
    session_id serial primary key ,
    user_id int references "user"(id),
    expiration_date timestamp not null
)
