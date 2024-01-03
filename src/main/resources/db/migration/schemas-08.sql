-- liquibase formatted sql

-- changeset luompv97:1704249975331-9
CREATE TABLE category
(
    id   BINARY(16) DEFAULT (uuid_to_bin(uuid())) NOT NULL,
    name VARCHAR(255)                             NOT NULL,
    CONSTRAINT pk_category PRIMARY KEY (id)
);

-- changeset luompv97:1704249975331-10
ALTER TABLE product
    ADD category_id BINARY(16) NULL;

-- changeset luompv97:1704249975331-11
ALTER TABLE product
    MODIFY category_id BINARY(16) NOT NULL;

-- changeset luompv97:1704249975331-12
ALTER TABLE shop
    ADD CONSTRAINT uc_shop_phone UNIQUE (phone);

-- changeset luompv97:1704249975331-13
ALTER TABLE product
    ADD CONSTRAINT FK_PRODUCT_ON_CATEGORY FOREIGN KEY (category_id) REFERENCES category (id);

-- changeset luompv97:1704249975331-1
ALTER TABLE product
    DROP COLUMN height;
ALTER TABLE product
    DROP COLUMN length;
ALTER TABLE product
    DROP COLUMN weight;
ALTER TABLE product
    DROP COLUMN width;

-- changeset luompv97:1704249975331-2
ALTER TABLE product
    ADD height INT NULL;

-- changeset luompv97:1704249975331-4
ALTER TABLE product
    ADD length INT NULL;

-- changeset luompv97:1704249975331-6
ALTER TABLE product
    ADD weight INT NULL;

-- changeset luompv97:1704249975331-8
ALTER TABLE product
    ADD width INT NULL;

