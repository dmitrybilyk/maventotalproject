create table PURCHASE_ORDERS (
    street varchar(255) not null,
    city varchar(255) not null,
    itemName varchar(255),
    primary key (street, city)
);