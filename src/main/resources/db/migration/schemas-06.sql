-- liquibase formatted sql

-- changeset luompv97:1704204076399-13
CREATE TABLE shop
(
    id            BINARY(16) DEFAULT (uuid_to_bin(uuid())) NOT NULL,
    shop_name     VARCHAR(255)                             NULL,
    phone         VARCHAR(255)                             NULL,
    address       VARCHAR(255)                             NULL,
    ward_name     VARCHAR(255)                             NULL,
    district_name VARCHAR(255)                             NULL,
    province_name VARCHAR(255)                             NULL,
    CONSTRAINT pk_shop PRIMARY KEY (id)
);

-- changeset luompv97:1704204076399-14
ALTER TABLE customer
    ADD shop_id BINARY(16) NULL;

-- changeset luompv97:1704204076399-15
ALTER TABLE customer
    ADD CONSTRAINT uc_customer_shop UNIQUE (shop_id);

-- changeset luompv97:1704204076399-16
ALTER TABLE customer
    ADD CONSTRAINT FK_CUSTOMER_ON_SHOP FOREIGN KEY (shop_id) REFERENCES shop (id);

-- changeset luompv97:1704204076399-1
ALTER TABLE payment
    MODIFY amount DOUBLE NULL;

-- changeset luompv97:1704204076399-11
ALTER TABLE order_pattern_detail
    DROP COLUMN status;

-- changeset luompv97:1704204076399-12
ALTER TABLE order_pattern_detail
    ADD status VARCHAR(255) NOT NULL;

