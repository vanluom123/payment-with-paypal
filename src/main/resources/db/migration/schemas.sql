-- liquibase formatted sql

-- changeset luompv97:1705310722053-1
CREATE TABLE cart
(
    id           BINARY(16) DEFAULT (uuid_to_bin(uuid())) NOT NULL,
    total_amount DOUBLE CHECK (total_amount > 0)          NOT NULL,
    customer_id  BINARY(16)                               NOT NULL,
    CONSTRAINT pk_cart PRIMARY KEY (id)
);

-- changeset luompv97:1705310722053-2
CREATE TABLE cart_detail
(
    id         BINARY(16) DEFAULT (uuid_to_bin(uuid())) NOT NULL,
    price      DOUBLE CHECK (price > 0)                 NOT NULL,
    quantity   INT        DEFAULT 1                     NOT NULL,
    product_id BINARY(16)                               NOT NULL,
    cart_id    BINARY(16)                               NOT NULL,
    CONSTRAINT pk_cart_detail PRIMARY KEY (id)
);

-- changeset luompv97:1705310722053-3
CREATE TABLE category
(
    id   BINARY(16) DEFAULT (uuid_to_bin(uuid())) NOT NULL,
    name NVARCHAR(255)                            NOT NULL,
    CONSTRAINT pk_category PRIMARY KEY (id)
);

-- changeset luompv97:1705310722053-4
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

-- changeset luompv97:1705310722053-5
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

-- changeset luompv97:1705310722053-6
CREATE TABLE file_modal
(
    product_id BINARY(16) DEFAULT (uuid_to_bin(uuid())) NOT NULL,
    bytes      LONGBLOB                                 NULL
);

-- changeset luompv97:1705310722053-7
CREATE TABLE pattern
(
    id            BINARY(16)  DEFAULT (uuid_to_bin(uuid())) NOT NULL,
    name          VARCHAR(255)                              NOT NULL,
    price         DOUBLE CHECK (price > 0)                  NOT NULL,
    currency_code VARCHAR(20) DEFAULT 'USD'                 NOT NULL,
    CONSTRAINT pk_pattern PRIMARY KEY (id)
);

-- changeset luompv97:1705310722053-8
CREATE TABLE pattern_files
(
    pattern_id BINARY(16) DEFAULT (uuid_to_bin(uuid())) NOT NULL,
    file_name  LONGBLOB                                 NULL
);

-- changeset luompv97:1705310722053-9
CREATE TABLE pattern_order
(
    id          BINARY(16) DEFAULT (uuid_to_bin(uuid())) NOT NULL,
    customer_id BINARY(16)                               NOT NULL,
    CONSTRAINT pk_pattern_order PRIMARY KEY (id)
);

-- changeset luompv97:1705310722053-10
CREATE TABLE pattern_order_detail
(
    id               BINARY(16) DEFAULT (uuid_to_bin(uuid())) NOT NULL,
    transaction_id   VARCHAR(255)                             NOT NULL,
    status           VARCHAR(255)                             NOT NULL,
    order_date       date                                     NOT NULL,
    pattern_order_id BINARY(16)                               NOT NULL,
    pattern_id       BINARY(16)                               NOT NULL,
    CONSTRAINT pk_pattern_order_detail PRIMARY KEY (id)
);

-- changeset luompv97:1705310722053-11
CREATE TABLE payment
(
    id               BINARY(16)  DEFAULT (uuid_to_bin(uuid())) NOT NULL,
    transaction_id   VARCHAR(255)                              NOT NULL,
    amount           DOUBLE CHECK (amount > 0)                 NOT NULL,
    payment_date     date                                      NOT NULL,
    payment_status   VARCHAR(25) DEFAULT 'CREATED'             NOT NULL,
    product_order_id BINARY(16)                                NOT NULL,
    CONSTRAINT pk_payment PRIMARY KEY (id)
);

-- changeset luompv97:1705310722053-12
CREATE TABLE product
(
    id            BINARY(16) DEFAULT (uuid_to_bin(uuid())) NOT NULL,
    name          VARCHAR(255)                             NOT NULL,
    price         DOUBLE CHECK (price > 0)                 NOT NULL,
    `description` LONGBLOB                                 NULL,
    length        INT                                      NULL,
    weight        INT                                      NULL,
    width         INT                                      NULL,
    height        INT                                      NULL,
    category_id   BINARY(16)                               NOT NULL,
    shop_id       BINARY(16)                               NOT NULL,
    CONSTRAINT pk_product PRIMARY KEY (id)
);

-- changeset luompv97:1705310722053-13
CREATE TABLE product_order
(
    id           BINARY(16)  DEFAULT (uuid_to_bin(uuid())) NOT NULL,
    status       VARCHAR(25) DEFAULT 'PENDING'             NOT NULL,
    total_amount DOUBLE CHECK (total_amount > 0)           NOT NULL,
    order_date   date                                      NOT NULL,
    customer_id  BINARY(16)                                NOT NULL,
    CONSTRAINT pk_product_order PRIMARY KEY (id)
);

-- changeset luompv97:1705310722053-14
CREATE TABLE product_order_detail
(
    id               BINARY(16) DEFAULT (uuid_to_bin(uuid())) NOT NULL,
    quantity         INT        DEFAULT 1                     NOT NULL,
    product_id       BINARY(16)                               NOT NULL,
    product_order_id BINARY(16)                               NOT NULL,
    CONSTRAINT pk_product_order_detail PRIMARY KEY (id)
);

-- changeset luompv97:1705310722053-15
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

-- changeset luompv97:1705310722053-16
ALTER TABLE cart
    ADD CONSTRAINT uc_cart_customer UNIQUE (customer_id);

-- changeset luompv97:1705310722053-17
ALTER TABLE category
    ADD CONSTRAINT uc_category_name UNIQUE (name);

-- changeset luompv97:1705310722053-18
ALTER TABLE customer
    ADD CONSTRAINT uc_customer_email UNIQUE (email);

-- changeset luompv97:1705310722053-19
ALTER TABLE pattern_order_detail
    ADD CONSTRAINT uc_pattern_order_detail_transaction UNIQUE (transaction_id);

-- changeset luompv97:1705310722053-20
ALTER TABLE payment
    ADD CONSTRAINT uc_payment_transaction UNIQUE (transaction_id);

-- changeset luompv97:1705310722053-21
ALTER TABLE shop
    ADD CONSTRAINT uc_shop_customer UNIQUE (customer_id);

-- changeset luompv97:1705310722053-22
ALTER TABLE shop
    ADD CONSTRAINT uc_shop_phone UNIQUE (phone);

-- changeset luompv97:1705310722053-23
ALTER TABLE cart_detail
    ADD CONSTRAINT FK_CART_DETAIL_ON_CART FOREIGN KEY (cart_id) REFERENCES cart (id);

-- changeset luompv97:1705310722053-24
ALTER TABLE cart_detail
    ADD CONSTRAINT FK_CART_DETAIL_ON_PRODUCT FOREIGN KEY (product_id) REFERENCES product (id);

-- changeset luompv97:1705310722053-25
ALTER TABLE cart
    ADD CONSTRAINT FK_CART_ON_CUSTOMER FOREIGN KEY (customer_id) REFERENCES customer (id);

-- changeset luompv97:1705310722053-26
ALTER TABLE contact
    ADD CONSTRAINT FK_CONTACT_ON_CUSTOMER FOREIGN KEY (customer_id) REFERENCES customer (id);

-- changeset luompv97:1705310722053-27
ALTER TABLE pattern_order_detail
    ADD CONSTRAINT FK_PATTERN_ORDER_DETAIL_ON_PATTERN FOREIGN KEY (pattern_id) REFERENCES pattern (id);

-- changeset luompv97:1705310722053-28
ALTER TABLE pattern_order_detail
    ADD CONSTRAINT FK_PATTERN_ORDER_DETAIL_ON_PATTERN_ORDER FOREIGN KEY (pattern_order_id) REFERENCES pattern_order (id);

-- changeset luompv97:1705310722053-29
ALTER TABLE pattern_order
    ADD CONSTRAINT FK_PATTERN_ORDER_ON_CUSTOMER FOREIGN KEY (customer_id) REFERENCES customer (id);

-- changeset luompv97:1705310722053-30
ALTER TABLE payment
    ADD CONSTRAINT FK_PAYMENT_ON_PRODUCT_ORDER FOREIGN KEY (product_order_id) REFERENCES product_order (id);

-- changeset luompv97:1705310722053-31
ALTER TABLE product
    ADD CONSTRAINT FK_PRODUCT_ON_CATEGORY FOREIGN KEY (category_id) REFERENCES category (id);

-- changeset luompv97:1705310722053-32
ALTER TABLE product
    ADD CONSTRAINT FK_PRODUCT_ON_SHOP FOREIGN KEY (shop_id) REFERENCES shop (id);

-- changeset luompv97:1705310722053-33
ALTER TABLE product_order_detail
    ADD CONSTRAINT FK_PRODUCT_ORDER_DETAIL_ON_PRODUCT FOREIGN KEY (product_id) REFERENCES product (id);

-- changeset luompv97:1705310722053-34
ALTER TABLE product_order_detail
    ADD CONSTRAINT FK_PRODUCT_ORDER_DETAIL_ON_PRODUCT_ORDER FOREIGN KEY (product_order_id) REFERENCES product_order (id);

-- changeset luompv97:1705310722053-35
ALTER TABLE product_order
    ADD CONSTRAINT FK_PRODUCT_ORDER_ON_CUSTOMER FOREIGN KEY (customer_id) REFERENCES customer (id);

-- changeset luompv97:1705310722053-36
ALTER TABLE shop
    ADD CONSTRAINT FK_SHOP_ON_CUSTOMER FOREIGN KEY (customer_id) REFERENCES customer (id);

-- changeset luompv97:1705310722053-37
ALTER TABLE file_modal
    ADD CONSTRAINT fk_file_modal_on_product FOREIGN KEY (product_id) REFERENCES product (id);

-- changeset luompv97:1705310722053-38
ALTER TABLE pattern_files
    ADD CONSTRAINT fk_pattern_files_on_pattern FOREIGN KEY (pattern_id) REFERENCES pattern (id);

