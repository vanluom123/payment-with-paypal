-- liquibase formatted sql

-- changeset luompv97:1701261122247-1
CREATE TABLE file_modal
(
    id                 BINARY(16) NOT NULL,
    name               VARCHAR(255) NULL,
    original_file_name VARCHAR(255) NULL,
    content_type       VARCHAR(255) NULL,
    bytes              BLOB NULL,
    product_id         BINARY(16) NOT NULL,
    CONSTRAINT pk_filemodal PRIMARY KEY (id)
);

-- changeset luompv97:1701261122247-2
ALTER TABLE file_modal
    ADD CONSTRAINT FK_FILEMODAL_ON_PRODUCT FOREIGN KEY (product_id) REFERENCES product (id);

