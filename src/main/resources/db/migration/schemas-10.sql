-- liquibase formatted sql

-- changeset luompv97:1704373040057-3
CREATE TABLE contact
(
    id            BINARY(16) DEFAULT (uuid_to_bin(uuid())) NOT NULL,
    address       VARCHAR(512) NOT NULL,
    phone         VARCHAR(32)  NOT NULL,
    ward_code     VARCHAR(255) NULL,
    ward_name     VARCHAR(255) NULL,
    district_id   INT NULL,
    district_name VARCHAR(255) NULL,
    province_name VARCHAR(255) NULL,
    customer_id   BINARY(16) NOT NULL,
    CONSTRAINT pk_contact PRIMARY KEY (id)
);

-- changeset luompv97:1704373040057-4
ALTER TABLE contact
    ADD CONSTRAINT FK_CONTACT_ON_CUSTOMER FOREIGN KEY (customer_id) REFERENCES customer (id);

-- changeset luompv97:1704373040057-5
ALTER TABLE customer DROP COLUMN address;
ALTER TABLE customer DROP COLUMN enabled;
ALTER TABLE customer DROP COLUMN phone;

