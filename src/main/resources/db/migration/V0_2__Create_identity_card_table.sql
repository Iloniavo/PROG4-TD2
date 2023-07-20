create table identity_card (
    id int primary key,
    number varchar not null,
    employee_id int references employee(id)
)