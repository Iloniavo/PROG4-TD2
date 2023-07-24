create table phone_number(
    id serial primary key,
    number varchar unique,
    owner_id int references employee(id)
)