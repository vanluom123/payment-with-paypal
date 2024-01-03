-- liquibase formatted sql

-- changeset luompv97:1704250669145-10
ALTER TABLE category
    ADD CONSTRAINT uc_category_name UNIQUE (name);

