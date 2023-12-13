-- liquibase formatted sql

-- changeset luompv97:1701229843164-1
CREATE TABLE customer
(
    id       BINARY(16) NOT NULL,
    name     VARCHAR(255) NOT NULL,
    address  VARCHAR(512) NOT NULL,
    email    VARCHAR(128) NOT NULL,
    phone    VARCHAR(32)  NOT NULL,
    username VARCHAR(255) NOT NULL,
    password VARCHAR(255) NOT NULL,
    enabled  BIT(1)       NOT NULL,
    `role`   VARCHAR(5) DEFAULT 'USER' NULL,
    CONSTRAINT pk_customer PRIMARY KEY (id)
);

-- changeset luompv97:1701229843164-2
CREATE TABLE orders
(
    id          BINARY(16) NOT NULL,
    orderdate   date NOT NULL,
    quantity    INT DEFAULT 1 NULL,
    customer_id BINARY(16) NOT NULL,
    product_id  BINARY(16) NOT NULL,
    CONSTRAINT pk_orders PRIMARY KEY (id)
);

-- changeset luompv97:1701229843164-3
CREATE TABLE product
(
    id            BINARY(16) NOT NULL,
    name          VARCHAR(255) NOT NULL,
    price DOUBLE NOT NULL,
    `description` LONGBLOB NULL,
    image         LONGBLOB NULL,
    CONSTRAINT pk_product PRIMARY KEY (id)
);

-- changeset luompv97:1701229843164-4
ALTER TABLE orders
    ADD CONSTRAINT FK_ORDERS_ON_CUSTOMER FOREIGN KEY (customer_id) REFERENCES customer (id);

-- changeset luompv97:1701229843164-5
ALTER TABLE orders
    ADD CONSTRAINT FK_ORDERS_ON_PRODUCT FOREIGN KEY (product_id) REFERENCES product (id);

