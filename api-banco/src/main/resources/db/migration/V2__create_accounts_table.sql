CREATE TABLE accounts (
    id BIGINT NOT NULL AUTO_INCREMENT,
    account_number VARCHAR(10) NOT NULL UNIQUE,
    agency VARCHAR(5) NOT NULL,
    current_balance DECIMAL(15, 2) NOT NULL,
    status_account BOOLEAN NOT NULL,
    client_id BIGINT NOT NULL UNIQUE,

    PRIMARY KEY (id),

    CONSTRAINT account_client
    FOREIGN KEY (client_id)
    REFERENCES clients(id)
);