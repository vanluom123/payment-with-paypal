-- liquibase formatted sql

-- changeset luompv97:1704718936916-1
CREATE TABLE cart
(
    id          BINARY(16) DEFAULT (uuid_to_bin(uuid())) NOT NULL,
    quantity    INT        DEFAULT 1                     NOT NULL,
    customer_id BINARY(16)                               NOT NULL,
    product_id  BINARY(16)                               NOT NULL,
    CONSTRAINT pk_cart PRIMARY KEY (id)
);

-- changeset luompv97:1704718936916-2
CREATE TABLE category
(
    id   BINARY(16) DEFAULT (uuid_to_bin(uuid())) NOT NULL,
    name NVARCHAR(255)                            NOT NULL,
    CONSTRAINT pk_category PRIMARY KEY (id)
);

-- changeset luompv97:1704718936916-3
CREATE TABLE contact
(
    id            BINARY(16) DEFAULT (uuid_to_bin(uuid())) NOT NULL,
    name          NVARCHAR(255)                            NOT NULL,
    address       NVARCHAR(512)                            NOT NULL,
    phone         VARCHAR(32)                              NOT NULL,
    ward_code     VARCHAR(255)                             NULL,
    ward_name     NVARCHAR(50)                             NULL,
    district_id   INT                                      NULL,
    district_name NVARCHAR(50)                             NULL,
    province_name NVARCHAR(50)                             NULL,
    is_default    BIT(1)     DEFAULT 0                     NULL,
    customer_id   BINARY(16)                               NOT NULL,
    CONSTRAINT pk_contact PRIMARY KEY (id)
);

-- changeset luompv97:1704718936916-4
CREATE TABLE customer
(
    id       BINARY(16) DEFAULT (uuid_to_bin(uuid())) NOT NULL,
    name     NVARCHAR(50)                             NULL,
    email    VARCHAR(128)                             NOT NULL,
    username VARCHAR(255)                             NOT NULL,
    password VARCHAR(255)                             NOT NULL,
    `role`   VARCHAR(5) DEFAULT 'USER'                NULL,
    CONSTRAINT pk_customer PRIMARY KEY (id)
);

-- changeset luompv97:1704718936916-5
CREATE TABLE file_modal
(
    product_id BINARY(16) NOT NULL,
    bytes      LONGBLOB   NULL
);

-- changeset luompv97:1704718936916-6
CREATE TABLE order_pattern
(
    id          BINARY(16) DEFAULT (uuid_to_bin(uuid())) NOT NULL,
    customer_id BINARY(16)                               NOT NULL,
    CONSTRAINT pk_order_pattern PRIMARY KEY (id)
);

-- changeset luompv97:1704718936916-7
CREATE TABLE order_pattern_detail
(
    id               BINARY(16) DEFAULT (uuid_to_bin(uuid())) NOT NULL,
    transaction_id   VARCHAR(255)                             NOT NULL,
    status           VARCHAR(255)                             NOT NULL,
    order_date       date                                     NOT NULL,
    order_pattern_id BINARY(16)                               NOT NULL,
    pattern_id       BINARY(16)                               NOT NULL,
    CONSTRAINT pk_order_pattern_detail PRIMARY KEY (id)
);

-- changeset luompv97:1704718936916-8
CREATE TABLE order_product
(
    id           BINARY(16)  DEFAULT (uuid_to_bin(uuid())) NOT NULL,
    status       VARCHAR(25) DEFAULT 'PENDING'             NOT NULL,
    total_amount DOUBLE      DEFAULT 0                     NOT NULL,
    order_date   date                                      NOT NULL,
    customer_id  BINARY(16)                                NOT NULL,
    CONSTRAINT pk_order_product PRIMARY KEY (id)
);

-- changeset luompv97:1704718936916-9
CREATE TABLE order_product_detail
(
    id               BINARY(16) DEFAULT (uuid_to_bin(uuid())) NOT NULL,
    quantity         INT        DEFAULT 1                     NOT NULL,
    product_id       BINARY(16)                               NOT NULL,
    order_product_id BINARY(16)                               NOT NULL,
    CONSTRAINT pk_order_product_detail PRIMARY KEY (id)
);

-- changeset luompv97:1704718936916-10
CREATE TABLE pattern
(
    id            BINARY(16)  DEFAULT (uuid_to_bin(uuid())) NOT NULL,
    name          VARCHAR(255)                              NOT NULL,
    price         DOUBLE      DEFAULT 0                     NOT NULL,
    currency_code VARCHAR(20) DEFAULT 'USD'                 NOT NULL,
    CONSTRAINT pk_pattern PRIMARY KEY (id)
);

-- changeset luompv97:1704718936916-11
CREATE TABLE pattern_files
(
    pattern_id BINARY(16) NOT NULL,
    file_name  LONGBLOB   NULL
);

-- changeset luompv97:1704718936916-12
CREATE TABLE payment
(
    id               BINARY(16)  DEFAULT (uuid_to_bin(uuid())) NOT NULL,
    transaction_id   VARCHAR(255)                              NOT NULL,
    amount           DOUBLE                                    NULL,
    payment_date     date                                      NOT NULL,
    payment_status   VARCHAR(25) DEFAULT 'CREATED'             NOT NULL,
    order_product_id BINARY(16)                                NOT NULL,
    CONSTRAINT pk_payment PRIMARY KEY (id)
);

-- changeset luompv97:1704718936916-13
CREATE TABLE product
(
    id            BINARY(16) DEFAULT (uuid_to_bin(uuid())) NOT NULL,
    name          VARCHAR(255)                             NOT NULL,
    price         DOUBLE                                   NOT NULL,
    `description` LONGBLOB                                 NULL,
    length        INT                                      NULL,
    weight        INT                                      NULL,
    width         INT                                      NULL,
    height        INT                                      NULL,
    category_id   BINARY(16)                               NOT NULL,
    shop_id       BINARY(16)                               NOT NULL,
    CONSTRAINT pk_product PRIMARY KEY (id)
);

-- changeset luompv97:1704718936916-14
CREATE TABLE shop
(
    id            BINARY(16) DEFAULT (uuid_to_bin(uuid())) NOT NULL,
    shop_name     NVARCHAR(255)                            NULL,
    phone         VARCHAR(255)                             NULL,
    address       NVARCHAR(255)                            NULL,
    ward_code     VARCHAR(255)                             NULL,
    ward_name     NVARCHAR(255)                            NULL,
    district_id   INT                                      NULL,
    district_name NVARCHAR(255)                            NULL,
    province_name NVARCHAR(255)                            NULL,
    customer_id   BINARY(16)                               NOT NULL,
    CONSTRAINT pk_shop PRIMARY KEY (id)
);

-- changeset luompv97:1704718936916-15
ALTER TABLE category
    ADD CONSTRAINT uc_category_name UNIQUE (name);

-- changeset luompv97:1704718936916-16
ALTER TABLE customer
    ADD CONSTRAINT uc_customer_email UNIQUE (email);

-- changeset luompv97:1704718936916-17
ALTER TABLE order_pattern_detail
    ADD CONSTRAINT uc_order_pattern_detail_transaction UNIQUE (transaction_id);

-- changeset luompv97:1704718936916-18
ALTER TABLE payment
    ADD CONSTRAINT uc_payment_transaction UNIQUE (transaction_id);

-- changeset luompv97:1704718936916-19
ALTER TABLE shop
    ADD CONSTRAINT uc_shop_customer UNIQUE (customer_id);

-- changeset luompv97:1704718936916-20
ALTER TABLE shop
    ADD CONSTRAINT uc_shop_phone UNIQUE (phone);

-- changeset luompv97:1704718936916-21
ALTER TABLE cart
    ADD CONSTRAINT FK_CART_ON_CUSTOMER FOREIGN KEY (customer_id) REFERENCES customer (id);

-- changeset luompv97:1704718936916-22
ALTER TABLE cart
    ADD CONSTRAINT FK_CART_ON_PRODUCT FOREIGN KEY (product_id) REFERENCES product (id);

-- changeset luompv97:1704718936916-23
ALTER TABLE contact
    ADD CONSTRAINT FK_CONTACT_ON_CUSTOMER FOREIGN KEY (customer_id) REFERENCES customer (id);

-- changeset luompv97:1704718936916-24
ALTER TABLE order_pattern_detail
    ADD CONSTRAINT FK_ORDER_PATTERN_DETAIL_ON_ORDER_PATTERN FOREIGN KEY (order_pattern_id) REFERENCES order_pattern (id);

-- changeset luompv97:1704718936916-25
ALTER TABLE order_pattern_detail
    ADD CONSTRAINT FK_ORDER_PATTERN_DETAIL_ON_PATTERN FOREIGN KEY (pattern_id) REFERENCES pattern (id);

-- changeset luompv97:1704718936916-26
ALTER TABLE order_pattern
    ADD CONSTRAINT FK_ORDER_PATTERN_ON_CUSTOMER FOREIGN KEY (customer_id) REFERENCES customer (id);

-- changeset luompv97:1704718936916-27
ALTER TABLE order_product_detail
    ADD CONSTRAINT FK_ORDER_PRODUCT_DETAIL_ON_ORDER_PRODUCT FOREIGN KEY (order_product_id) REFERENCES order_product (id);

-- changeset luompv97:1704718936916-28
ALTER TABLE order_product_detail
    ADD CONSTRAINT FK_ORDER_PRODUCT_DETAIL_ON_PRODUCT FOREIGN KEY (product_id) REFERENCES product (id);

-- changeset luompv97:1704718936916-29
ALTER TABLE order_product
    ADD CONSTRAINT FK_ORDER_PRODUCT_ON_CUSTOMER FOREIGN KEY (customer_id) REFERENCES customer (id);

-- changeset luompv97:1704718936916-30
ALTER TABLE payment
    ADD CONSTRAINT FK_PAYMENT_ON_ORDER_PRODUCT FOREIGN KEY (order_product_id) REFERENCES order_product (id);

-- changeset luompv97:1704718936916-31
ALTER TABLE product
    ADD CONSTRAINT FK_PRODUCT_ON_CATEGORY FOREIGN KEY (category_id) REFERENCES category (id);

-- changeset luompv97:1704718936916-32
ALTER TABLE product
    ADD CONSTRAINT FK_PRODUCT_ON_SHOP FOREIGN KEY (shop_id) REFERENCES shop (id);

-- changeset luompv97:1704718936916-33
ALTER TABLE shop
    ADD CONSTRAINT FK_SHOP_ON_CUSTOMER FOREIGN KEY (customer_id) REFERENCES customer (id);

-- changeset luompv97:1704718936916-34
ALTER TABLE file_modal
    ADD CONSTRAINT fk_file_modal_on_product FOREIGN KEY (product_id) REFERENCES product (id);

-- changeset luompv97:1704718936916-35
ALTER TABLE pattern_files
    ADD CONSTRAINT fk_pattern_files_on_pattern FOREIGN KEY (pattern_id) REFERENCES pattern (id);

