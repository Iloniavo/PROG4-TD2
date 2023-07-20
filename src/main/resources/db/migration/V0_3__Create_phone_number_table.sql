create table phone_number(
    id int primary key,
    number varchar unique,
    owner int references employee(id)
)