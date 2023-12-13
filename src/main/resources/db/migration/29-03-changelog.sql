-- liquibase formatted sql

-- changeset luompv97:1701265183341-1
ALTER TABLE file_modal DROP COLUMN bytes;

-- changeset luompv97:1701265183341-2
ALTER TABLE file_modal
    ADD bytes LONGBLOB NULL;

