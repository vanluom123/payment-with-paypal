-- liquibase formatted sql

-- changeset luompv97:1702961406678-1
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

-- changeset luompv97:1702961406678-2
CREATE TABLE file_modal
(
    id                 BINARY(16) NOT NULL,
    name               VARCHAR(255) NULL,
    original_file_name VARCHAR(255) NULL,
    content_type       VARCHAR(255) NULL,
    bytes              LONGBLOB NULL,
    product_id         BINARY(16) NOT NULL,
    CONSTRAINT pk_file_modal PRIMARY KEY (id)
);

-- changeset luompv97:1702961406678-3
CREATE TABLE order_pattern_detail
(
    id             BINARY(16) NOT NULL,
    transaction_id VARCHAR(255) NOT NULL,
    status         VARCHAR(255) NOT NULL,
    order_date     date         NOT NULL,
    order_id       BINARY(16) NOT NULL,
    pattern_id     BINARY(16) NOT NULL,
    CONSTRAINT pk_order_pattern_detail PRIMARY KEY (id)
);

-- changeset luompv97:1702961406678-4
CREATE TABLE order_product_detail
(
    id             BINARY(16) NOT NULL,
    transaction_id VARCHAR(255) NOT NULL,
    order_date     date         NOT NULL,
    quantity       INT DEFAULT 1 NULL,
    price DOUBLE DEFAULT 0 NULL,
    product_id     BINARY(16) NOT NULL,
    order_id       BINARY(16) NOT NULL,
    CONSTRAINT pk_order_product_detail PRIMARY KEY (id)
);

-- changeset luompv97:1702961406678-5
CREATE TABLE orders
(
    id          BINARY(16) NOT NULL,
    customer_id BINARY(16) NOT NULL,
    CONSTRAINT pk_orders PRIMARY KEY (id)
);

-- changeset luompv97:1702961406678-6
CREATE TABLE pattern
(
    id   BINARY(16) NOT NULL,
    name VARCHAR(255) NOT NULL,
    price DOUBLE DEFAULT 0 NOT NULL,
    CONSTRAINT pk_pattern PRIMARY KEY (id)
);

-- changeset luompv97:1702961406678-7
CREATE TABLE pattern_files
(
    pattern_id BINARY(16) NOT NULL,
    file_name  VARCHAR(255) NULL
);

-- changeset luompv97:1702961406678-8
CREATE TABLE product
(
    id            BINARY(16) NOT NULL,
    name          VARCHAR(255) NOT NULL,
    price DOUBLE NOT NULL,
    `description` LONGBLOB NULL,
    CONSTRAINT pk_product PRIMARY KEY (id)
);

-- changeset luompv97:1702961406678-9
ALTER TABLE file_modal
    ADD CONSTRAINT FK_FILE_MODAL_ON_PRODUCT FOREIGN KEY (product_id) REFERENCES product (id);

-- changeset luompv97:1702961406678-10
ALTER TABLE orders
    ADD CONSTRAINT FK_ORDERS_ON_CUSTOMER FOREIGN KEY (customer_id) REFERENCES customer (id);

-- changeset luompv97:1702961406678-11
ALTER TABLE order_pattern_detail
    ADD CONSTRAINT FK_ORDER_PATTERN_DETAIL_ON_ORDER FOREIGN KEY (order_id) REFERENCES orders (id);

-- changeset luompv97:1702961406678-12
ALTER TABLE order_pattern_detail
    ADD CONSTRAINT FK_ORDER_PATTERN_DETAIL_ON_PATTERN FOREIGN KEY (pattern_id) REFERENCES pattern (id);

-- changeset luompv97:1702961406678-13
ALTER TABLE order_product_detail
    ADD CONSTRAINT FK_ORDER_PRODUCT_DETAIL_ON_ORDER FOREIGN KEY (order_id) REFERENCES orders (id);

-- changeset luompv97:1702961406678-14
ALTER TABLE order_product_detail
    ADD CONSTRAINT FK_ORDER_PRODUCT_DETAIL_ON_PRODUCT FOREIGN KEY (product_id) REFERENCES product (id);

-- changeset luompv97:1702961406678-15
ALTER TABLE pattern_files
    ADD CONSTRAINT fk_pattern_files_on_pattern FOREIGN KEY (pattern_id) REFERENCES pattern (id);

