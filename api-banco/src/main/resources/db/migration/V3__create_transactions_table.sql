create table transactions(
    id bigint not null auto_increment,
    type_transaction varchar(30) not null,
    value_amount decimal(10,2) not null,
    date_transaction datetime not null,
    source_account_id bigint,
    destination_account_id bigint,

    primary key (id),

    constraint transaction_source_account
                         foreign key (source_account_id)
                         references accounts(id),

    constraint transaction_destination_account
                         foreign key (destination_account_id)
                         references accounts(id)
);