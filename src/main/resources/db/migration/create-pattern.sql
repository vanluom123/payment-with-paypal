-- liquibase formatted sql

-- changeset luompv97:1702889790403-1
CREATE TABLE pattern
(
    id   BINARY(16) NOT NULL,
    name VARCHAR(255) NOT NULL,
    price DOUBLE DEFAULT 0 NOT NULL,
    CONSTRAINT pk_pattern PRIMARY KEY (id)
);

-- changeset luompv97:1702889790403-2
CREATE TABLE product_files
(
    product_id BINARY(16) NOT NULL,
    file_name  VARCHAR(255) NULL
);

-- changeset luompv97:1702889790403-3
ALTER TABLE product_files
    ADD CONSTRAINT fk_product_files_on_pattern FOREIGN KEY (product_id) REFERENCES pattern (id);

