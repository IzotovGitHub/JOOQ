create table addresses
(
    id bigserial not null primary key,
    street varchar(255)
);

create table clients
(
    id bigserial not null primary key,
    name varchar(255),
    address_id bigint,
    FOREIGN KEY (address_id) REFERENCES addresses (id)
);

create table phones
(
    id   bigserial not null primary key,
    number varchar(255),
    client_id bigint,
    FOREIGN KEY (client_id) REFERENCES clients (id)
);