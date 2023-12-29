-- liquibase formatted sql

-- changeset luompv97:1703862886637-11
CREATE TABLE payment
(
    id               BINARY(16)  DEFAULT (uuid_to_bin(uuid())) NOT NULL,
    transaction_id   VARCHAR(255)                              NOT NULL,
    amount           DOUBLE                                    NOT NULL,
    payment_date     date                                      NOT NULL,
    payment_status   VARCHAR(25) DEFAULT 'CREATED'             NOT NULL,
    order_product_id BINARY(16)                                NOT NULL,
    CONSTRAINT pk_payment PRIMARY KEY (id)
);

-- changeset luompv97:1703862886637-12
ALTER TABLE order_pattern_detail
    ADD CONSTRAINT uc_order_pattern_detail_transaction UNIQUE (transaction_id);

-- changeset luompv97:1703862886637-13
ALTER TABLE payment
    ADD CONSTRAINT uc_payment_transaction UNIQUE (transaction_id);

-- changeset luompv97:1703862886637-14
ALTER TABLE payment
    ADD CONSTRAINT FK_PAYMENT_ON_ORDER_PRODUCT FOREIGN KEY (order_product_id) REFERENCES order_product (id);

