-- liquibase formatted sql

-- changeset luompv97:1703858893687-1
ALTER TABLE cart
    ALTER customer_id SET DEFAULT (uuid_to_bin(uuid()));

-- changeset luompv97:1703858893687-2
ALTER TABLE order_pattern
    ALTER customer_id SET DEFAULT (uuid_to_bin(uuid()));

-- changeset luompv97:1703858893687-3
ALTER TABLE order_product
    ALTER customer_id SET DEFAULT (uuid_to_bin(uuid()));

-- changeset luompv97:1703858893687-4
ALTER TABLE cart
    ALTER id SET DEFAULT (uuid_to_bin(uuid()));

-- changeset luompv97:1703858893687-5
ALTER TABLE customer
    ALTER id SET DEFAULT (uuid_to_bin(uuid()));

-- changeset luompv97:1703858893687-6
ALTER TABLE order_pattern
    ALTER id SET DEFAULT (uuid_to_bin(uuid()));

-- changeset luompv97:1703858893687-7
ALTER TABLE order_pattern_detail
    ALTER id SET DEFAULT (uuid_to_bin(uuid()));

-- changeset luompv97:1703858893687-8
ALTER TABLE order_product
    ALTER id SET DEFAULT (uuid_to_bin(uuid()));

-- changeset luompv97:1703858893687-9
ALTER TABLE order_product_detail
    ALTER id SET DEFAULT (uuid_to_bin(uuid()));

-- changeset luompv97:1703858893687-10
ALTER TABLE pattern
    ALTER id SET DEFAULT (uuid_to_bin(uuid()));

-- changeset luompv97:1703858893687-11
ALTER TABLE product
    ALTER id SET DEFAULT (uuid_to_bin(uuid()));

-- changeset luompv97:1703858893687-12
ALTER TABLE order_pattern_detail
    ALTER order_pattern_id SET DEFAULT (uuid_to_bin(uuid()));

-- changeset luompv97:1703858893687-13
ALTER TABLE order_product_detail
    ALTER order_product_id SET DEFAULT (uuid_to_bin(uuid()));

-- changeset luompv97:1703858893687-14
ALTER TABLE order_pattern_detail
    ALTER pattern_id SET DEFAULT (uuid_to_bin(uuid()));

-- changeset luompv97:1703858893687-15
ALTER TABLE pattern_files
    ALTER pattern_id SET DEFAULT (uuid_to_bin(uuid()));

-- changeset luompv97:1703858893687-16
ALTER TABLE cart
    ALTER product_id SET DEFAULT (uuid_to_bin(uuid()));

-- changeset luompv97:1703858893687-17
ALTER TABLE file_modal
    ALTER product_id SET DEFAULT (uuid_to_bin(uuid()));

-- changeset luompv97:1703858893687-18
ALTER TABLE order_product_detail
    ALTER product_id SET DEFAULT (uuid_to_bin(uuid()));

-- changeset luompv97:1703858893687-19
ALTER TABLE order_pattern_detail DROP COLUMN status;

-- changeset luompv97:1703858893687-20
ALTER TABLE order_pattern_detail
    ADD status VARCHAR(255) NOT NULL;

