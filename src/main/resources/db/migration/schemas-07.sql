-- liquibase formatted sql

-- changeset luompv97:1704213131989-13
ALTER TABLE shop
    ADD district_id INT          NULL,
    ADD ward_code   VARCHAR(255) NULL;

-- changeset luompv97:1704213131989-1
ALTER TABLE cart
    ALTER id SET DEFAULT (uuid_to_bin(uuid()));

-- changeset luompv97:1704213131989-2
ALTER TABLE customer
    ALTER id SET DEFAULT (uuid_to_bin(uuid()));

-- changeset luompv97:1704213131989-3
ALTER TABLE order_pattern
    ALTER id SET DEFAULT (uuid_to_bin(uuid()));

-- changeset luompv97:1704213131989-4
ALTER TABLE order_pattern_detail
    ALTER id SET DEFAULT (uuid_to_bin(uuid()));

-- changeset luompv97:1704213131989-5
ALTER TABLE order_product
    ALTER id SET DEFAULT (uuid_to_bin(uuid()));

-- changeset luompv97:1704213131989-6
ALTER TABLE order_product_detail
    ALTER id SET DEFAULT (uuid_to_bin(uuid()));

-- changeset luompv97:1704213131989-7
ALTER TABLE pattern
    ALTER id SET DEFAULT (uuid_to_bin(uuid()));

-- changeset luompv97:1704213131989-8
ALTER TABLE product
    ALTER id SET DEFAULT (uuid_to_bin(uuid()));

-- changeset luompv97:1704213131989-9
ALTER TABLE payment
    ALTER order_product_id SET DEFAULT (uuid_to_bin(uuid()));

-- changeset luompv97:1704213131989-10
ALTER TABLE customer
    ALTER shop_id SET DEFAULT (uuid_to_bin(uuid()));

-- changeset luompv97:1704213131989-11
ALTER TABLE order_pattern_detail
    DROP COLUMN status;

-- changeset luompv97:1704213131989-12
ALTER TABLE order_pattern_detail
    ADD status VARCHAR(255) NOT NULL;

