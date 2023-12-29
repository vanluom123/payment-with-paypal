-- liquibase formatted sql

-- changeset luompv97:1703856077013-1
CREATE TABLE cart
(
    id          BINARY(16)    NOT NULL,
    quantity    INT DEFAULT 1 NOT NULL,
    customer_id BINARY(16)    NOT NULL,
    product_id  BINARY(16)    NOT NULL,
    CONSTRAINT pk_cart PRIMARY KEY (id)
);

-- changeset luompv97:1703856077013-2
CREATE TABLE customer
(
    id       BINARY(16)                NOT NULL,
    name     VARCHAR(255)              NOT NULL,
    address  VARCHAR(512)              NOT NULL,
    email    VARCHAR(128)              NOT NULL,
    phone    VARCHAR(32)               NOT NULL,
    username VARCHAR(255)              NOT NULL,
    password VARCHAR(255)              NOT NULL,
    enabled  BIT(1)                    NOT NULL,
    `role`   VARCHAR(5) DEFAULT 'USER' NULL,
    CONSTRAINT pk_customer PRIMARY KEY (id)
);

-- changeset luompv97:1703856077013-3
CREATE TABLE file_modal
(
    product_id BINARY(16) NOT NULL,
    bytes      LONGBLOB   NULL
);

-- changeset luompv97:1703856077013-4
CREATE TABLE order_pattern
(
    id          BINARY(16) NOT NULL,
    customer_id BINARY(16) NOT NULL,
    CONSTRAINT pk_order_pattern PRIMARY KEY (id)
);

-- changeset luompv97:1703856077013-5
CREATE TABLE order_pattern_detail
(
    id               BINARY(16)   NOT NULL,
    transaction_id   VARCHAR(255) NOT NULL,
    status           VARCHAR(255) NOT NULL,
    order_date       date         NOT NULL,
    order_pattern_id BINARY(16)   NOT NULL,
    pattern_id       BINARY(16)   NOT NULL,
    CONSTRAINT pk_order_pattern_detail PRIMARY KEY (id)
);

-- changeset luompv97:1703856077013-6
CREATE TABLE order_product
(
    id             BINARY(16)                    NOT NULL,
    transaction_id VARCHAR(255)                  NOT NULL,
    status         VARCHAR(25) DEFAULT 'PENDING' NOT NULL,
    total_amount   DOUBLE      DEFAULT 0         NOT NULL,
    order_date     date                          NOT NULL,
    customer_id    BINARY(16)                    NOT NULL,
    CONSTRAINT pk_order_product PRIMARY KEY (id)
);

-- changeset luompv97:1703856077013-7
CREATE TABLE order_product_detail
(
    id               BINARY(16)    NOT NULL,
    quantity         INT DEFAULT 1 NOT NULL,
    product_id       BINARY(16)    NOT NULL,
    order_product_id BINARY(16)    NOT NULL,
    CONSTRAINT pk_order_product_detail PRIMARY KEY (id)
);

-- changeset luompv97:1703856077013-8
CREATE TABLE pattern
(
    id            BINARY(16)                NOT NULL,
    name          VARCHAR(255)              NOT NULL,
    price         DOUBLE      DEFAULT 0     NOT NULL,
    currency_code VARCHAR(20) DEFAULT 'USD' NOT NULL,
    CONSTRAINT pk_pattern PRIMARY KEY (id)
);

-- changeset luompv97:1703856077013-9
CREATE TABLE pattern_files
(
    pattern_id BINARY(16) NOT NULL,
    file_name  LONGBLOB   NULL
);

-- changeset luompv97:1703856077013-10
CREATE TABLE product
(
    id            BINARY(16)   NOT NULL,
    name          VARCHAR(255) NOT NULL,
    price         DOUBLE       NOT NULL,
    `description` LONGBLOB     NULL,
    CONSTRAINT pk_product PRIMARY KEY (id)
);

-- changeset luompv97:1703856077013-11
ALTER TABLE cart
    ADD CONSTRAINT FK_CART_ON_CUSTOMER FOREIGN KEY (customer_id) REFERENCES customer (id);

-- changeset luompv97:1703856077013-12
ALTER TABLE cart
    ADD CONSTRAINT FK_CART_ON_PRODUCT FOREIGN KEY (product_id) REFERENCES product (id);

-- changeset luompv97:1703856077013-13
ALTER TABLE order_pattern_detail
    ADD CONSTRAINT FK_ORDER_PATTERN_DETAIL_ON_ORDER_PATTERN FOREIGN KEY (order_pattern_id) REFERENCES order_pattern (id);

-- changeset luompv97:1703856077013-14
ALTER TABLE order_pattern_detail
    ADD CONSTRAINT FK_ORDER_PATTERN_DETAIL_ON_PATTERN FOREIGN KEY (pattern_id) REFERENCES pattern (id);

-- changeset luompv97:1703856077013-15
ALTER TABLE order_pattern
    ADD CONSTRAINT FK_ORDER_PATTERN_ON_CUSTOMER FOREIGN KEY (customer_id) REFERENCES customer (id);

-- changeset luompv97:1703856077013-16
ALTER TABLE order_product_detail
    ADD CONSTRAINT FK_ORDER_PRODUCT_DETAIL_ON_ORDER_PRODUCT FOREIGN KEY (order_product_id) REFERENCES order_product (id);

-- changeset luompv97:1703856077013-17
ALTER TABLE order_product_detail
    ADD CONSTRAINT FK_ORDER_PRODUCT_DETAIL_ON_PRODUCT FOREIGN KEY (product_id) REFERENCES product (id);

-- changeset luompv97:1703856077013-18
ALTER TABLE order_product
    ADD CONSTRAINT FK_ORDER_PRODUCT_ON_CUSTOMER FOREIGN KEY (customer_id) REFERENCES customer (id);

-- changeset luompv97:1703856077013-19
ALTER TABLE file_modal
    ADD CONSTRAINT fk_file_modal_on_product FOREIGN KEY (product_id) REFERENCES product (id);

-- changeset luompv97:1703856077013-20
ALTER TABLE pattern_files
    ADD CONSTRAINT fk_pattern_files_on_pattern FOREIGN KEY (pattern_id) REFERENCES pattern (id);

