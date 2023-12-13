-- liquibase formatted sql

-- changeset luompv97:1702508430559-1
ALTER TABLE order_details
    ADD transaction_id VARCHAR(255) NULL;

