create type sex_enum as enum ('H', 'F');
create type social_category_type as enum ('M1', 'M2', 'OS1', 'OS2', 'OS3', 'OP1');

create table employee(
    id int primary key,
    first_name varchar not null,
    last_name varchar not null,
    serial_number varchar not null unique,
    birthday date,
    pic_url text,
    sex sex_enum not null,
    location varchar not null,
    pro_email varchar not null,
    ps_email varchar,
    function varchar,
    social_category social_category_type not null,
    dependent_children int not null ,
    cnaps_serial_number varchar not null,
    hire_date date not null,
    resignation_date date not null
)