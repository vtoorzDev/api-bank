create table clients(
    id bigint not null auto_increment,
    name varchar(250),
    cpf varchar(11)not null unique,
    phone varchar (20) not null,
    wage decimal(10,2) not null,
    primary key (id)
);