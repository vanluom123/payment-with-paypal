-- liquibase formatted sql

-- changeset luompv97:1702897042773-1
CREATE TABLE payment_pattern
(
    id             BINARY(16) NOT NULL,
    transaction_id VARCHAR(255) NOT NULL,
    status         VARCHAR(255) NOT NULL,
    payment_date   date         NOT NULL,
    total_amount DOUBLE DEFAULT 0 NOT NULL,
    customer_id    BINARY(16) NOT NULL,
    pattern_id     BINARY(16) NOT NULL,
    CONSTRAINT pk_payment_pattern PRIMARY KEY (id)
);

-- changeset luompv97:1702897042773-2
ALTER TABLE payment_pattern
    ADD CONSTRAINT FK_PAYMENT_PATTERN_ON_CUSTOMER FOREIGN KEY (customer_id) REFERENCES customer (id);

-- changeset luompv97:1702897042773-3
ALTER TABLE payment_pattern
    ADD CONSTRAINT FK_PAYMENT_PATTERN_ON_PATTERN FOREIGN KEY (pattern_id) REFERENCES pattern (id);

